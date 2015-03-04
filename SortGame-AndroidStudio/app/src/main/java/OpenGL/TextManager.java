package OpenGL;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Vector;

public class TextManager
{
	
	private static final float RI_TEXT_UV_BOX_WIDTH = 0.125f,
	                           RI_TEXT_WIDTH = 27.0f,
                               RI_TEXT_SPACESIZE = 10f;

    private static final int[] l_size = { 36,29,30,34,25,25,34,33,
                                          11,20,31,24,48,35,39,29,
                                          42,31,27,31,34,35,46,35,
                                          31,27,30,26,28,26,31,28,
                                          28,28,29,29,14,24,30,18,
                                          26,14,14,14,25,28,31, 0,
                                           0,38,39,12,36,34, 0, 0,
                                           0,38, 0, 0, 0, 0, 0, 0
                                        };


	
	public FloatBuffer vertexBuffer,
	                    textureBuffer,
	                    boldnessBuffer;

    public ShortBuffer drawListBuffer;



	private float[] vecs,
                    uvs,
                    boldness;

    private short[] indices;


	private int index_vecs,
	            index_indices,
	            index_uvs,
                index_boldness,
                texturenr;
	
	private float uniformscale;
	

	
	private Vector<TextObject> textCollection;

	public TextManager()
	{
		// Create our container
		this.textCollection = new Vector<>();
		
		// Create the arrays
		this.boldness = new float[4 * 10];
		this.uvs = new float[2 * 10];
		this.indices = new short[10];
		
		// init as 0 as default
		this.texturenr = 0;
	}


	private void prepareDraw()
	{
		// Setup all the arrays
		this.prepareDrawInfo();


		// Using the iterator protects for problems with concurrency
        for (TextObject textObject : this.textCollection)
        {
            if(textObject != null)
            {
                String text = textObject.getText();

                if(text != null)
                    this.convertTextToTriangleInfo(textObject);

            }

	    }
	}

    private void prepareDrawInfo()
    {
        // Reset the indices.
        this.index_vecs = 0;
        this.index_indices = 0;
        this.index_uvs = 0;
        this.index_boldness = 0;

        // Get the total amount of characters
        int charcount = 0;

        for (TextObject textObject : this.textCollection)
        {
            if(textObject != null)
            {
                String text = textObject.getText();
                Log.i("In TM text = ", text);

                if(text != null)
                    charcount += text.length();
            }

        }

        // Create the arrays we need with the correct size.
        this.vecs = null;
        this.boldness = null;
        this.uvs = null;
        this.indices = null;

        this.vecs = new float[charcount * 12];
        this.boldness = new float[charcount * 16];
        this.uvs = new float[charcount * 8];
        this.indices = new short[charcount * 6];

    }

    private void convertTextToTriangleInfo(TextObject textObject)
    {
        // Get attributes from text object
        float x = textObject.getxCoordiates();
        float y = textObject.getyCoordiates();
        String text = textObject.getText();

        // for each character in the text
        for(int j = 0; j < text.length(); j++)
        {
            // get ascii value
            char c = text.charAt(j);
            int c_val = (int)c;

            // find the index of that char relative to sprite sheet
            int index = convertCharToIndex(c_val);

            // unknown character, we will add a space for it to be save.
            if(index==-1)
                x += ((RI_TEXT_SPACESIZE) * this.uniformscale);


            // Calculation of u and v parts to get the correct position
            else
            {

                // find the row and col for each char
                int row = index / 8;
                int col = index % 8;

                // calculate the height of the character
                float v = row * RI_TEXT_UV_BOX_WIDTH;
                float v2 = v + RI_TEXT_UV_BOX_WIDTH;

                // calculate the width of the character
                float u = col * RI_TEXT_UV_BOX_WIDTH;
                float u2 = u + RI_TEXT_UV_BOX_WIDTH;



                // Creating the triangle information
                float[] vec = new float[12];

                vec[0] = x;
                vec[1] = y + (RI_TEXT_WIDTH * uniformscale);
                vec[2] = 0.99f;
                vec[3] = x;
                vec[4] = y;
                vec[5] = 0.99f;
                vec[6] = x + (RI_TEXT_WIDTH * uniformscale);
                vec[7] = y;
                vec[8] = 0.99f;
                vec[9] = x + (RI_TEXT_WIDTH * uniformscale);
                vec[10] = y + (RI_TEXT_WIDTH * uniformscale);
                vec[11] = 0.99f;



                // get the boldness for the text
                float boldnessPos0 = textObject.getBoldness()[0],
                      boldnessPos1 = textObject.getBoldness()[1],
                      boldnessPos2 = textObject.getBoldness()[2],
                      boldnessPos3 = textObject.getBoldness()[3];


                // create the boldness array
                float[] boldness = { boldnessPos0, boldnessPos1, boldnessPos2, boldnessPos3,
                                     boldnessPos0, boldnessPos1, boldnessPos2, boldnessPos3,
                                     boldnessPos0, boldnessPos1, boldnessPos2, boldnessPos3,
                                     boldnessPos0, boldnessPos1, boldnessPos2, boldnessPos3,
                                   };


                float[] uv = { u, v, u, v2, u2, v2, u2, v};

                short[] indices = {0, 1, 2, 0, 2, 3};

                // Add our triangle information to our collection for 1 render call.
                this.addCharRenderInformation(vec, boldness, uv, indices);

                // Calculate the new position
                x += ((l_size[index] / 2) * this.uniformscale);
            }
        }
    }


    private void addCharRenderInformation(float[] vec, float[] bold, float[] uv, short[] indices)
    {
        // We need a base value because the object has indices related to
        // that object and not to this collection so basicly we need to
        // translate the indices to align with the vertexlocation in ou
        // vecs array of vectors.
        short base = (short) (this.index_vecs / 3);

        // We should add the vec, translating the indices to our saved vector
        for(int i = 0; i < vec.length; i++)
        {
            this.vecs[this.index_vecs] = vec[i];
            this.index_vecs ++;
        }

        // We should add the colors, so we can use the same texture for multiple effects.
        for(int i = 0; i < bold.length; i++)
        {
            this.boldness[this.index_boldness] = bold[i];
            this.index_boldness ++;
        }

        // We should add the uvs
        for(int i = 0; i < uv.length; i++)
        {
            this.uvs[this.index_uvs] = uv[i];
            this.index_uvs ++;
        }

        // We handle the indices
        for(int j = 0; j < indices.length; j++)
        {
            this.indices[this.index_indices] = (short) (base + indices[j]);
            this.index_indices ++;
        }
    }

	
	private int convertCharToIndex(int c_val)
	{
		int index = -1;
		
		// Retrieve the index
		if(c_val > 64 && c_val < 91) // A-Z
            index = c_val - 65;

        else if(c_val > 96 && c_val < 123) // a-z
            index = c_val - 97;

        else if(c_val > 47 && c_val < 58) // 0-9
            index = c_val - 48 + 26;

        else if(c_val == 43) // +
            index = 38;

        else if(c_val == 45) // -
            index = 39;

        else if(c_val == 33) // !
			index = 36;

        else if(c_val == 63) // ?
			index = 37;

        else if(c_val == 61) // =
			index = 40;

        else if(c_val == 58) // :
			index = 41;

        else if(c_val == 46) // .
			index = 42;

        else if(c_val== 44) // ,
			index = 43;

        else if(c_val== 42) // *
			index = 44;

        else if(c_val == 36) // $
			index = 45;
		
		return index;
	}


    private void initializeBuffers()
    {
        // The vertex buffer.
        ByteBuffer bb = ByteBuffer.allocateDirect(this.vecs.length * 4);
        bb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = bb.asFloatBuffer();
        this.vertexBuffer.put(this.vecs);
        this.vertexBuffer.position(0);

        // The boldness buffer.
        ByteBuffer bb3 = ByteBuffer.allocateDirect(this.boldness.length * 4);
        bb3.order(ByteOrder.nativeOrder());
        this.boldnessBuffer = bb3.asFloatBuffer();
        this.boldnessBuffer.put(this.boldness);
        this.boldnessBuffer.position(0);

        // The texture buffer
        ByteBuffer bb2 = ByteBuffer.allocateDirect(this.uvs.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        this.textureBuffer = bb2.asFloatBuffer();
        this.textureBuffer.put(this.uvs);
        this.textureBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(this.indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        this.drawListBuffer = dlb.asShortBuffer();
        this.drawListBuffer.put(this.indices);
        this.drawListBuffer.position(0);

    }
	
	


	public float getUniformscale()
    {
		return uniformscale;
	}

	public void setUniformscale(float uniformscale)
    {
		this.uniformscale = uniformscale;
	}

    public void addText(TextObject obj)
    {
        // Add text object to our collection
        this.textCollection.add(obj);
    }

    public void setTextureID(int val)
    {
        this.texturenr = val;
    }

    public int getTexturenr()
    {
        return this.texturenr;
    }

    public void draw(float[] m)
    {
        this.prepareDraw();

        // Set the correct shader for our grid object.
        GLES20.glUseProgram(riGraphicTools.sp_Text);

        this.initializeBuffers();

        // get handle to vertex shader's vPosition member
        int mPositionHandle = GLES20.glGetAttribLocation(riGraphicTools.sp_Text, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the background coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 0, this.vertexBuffer);

        int mTexCoordLoc = GLES20.glGetAttribLocation(riGraphicTools.sp_Text, "a_texCoord" );

        // Prepare the texturecoordinates
        GLES20.glVertexAttribPointer(mTexCoordLoc, 2, GLES20.GL_FLOAT, false, 0, this.textureBuffer);

        GLES20.glEnableVertexAttribArray ( mPositionHandle );
        GLES20.glEnableVertexAttribArray ( mTexCoordLoc );

        int mColorHandle = GLES20.glGetAttribLocation(riGraphicTools.sp_Text, "a_Color");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mColorHandle);

        // Prepare the background coordinate data
        GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false, 0, this.boldnessBuffer);

        // get handle to shape's transformation matrix
        int mtrxhandle = GLES20.glGetUniformLocation(riGraphicTools.sp_Text, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);

        int mSamplerLoc = GLES20.glGetUniformLocation (riGraphicTools.sp_Text, "s_texture" );

        // Set the sampler texture unit to our selected id
        GLES20.glUniform1i ( mSamplerLoc, this.texturenr);

        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, this.indices.length, GLES20.GL_UNSIGNED_SHORT, this.drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mTexCoordLoc);
        GLES20.glDisableVertexAttribArray(mColorHandle);

    }
}
