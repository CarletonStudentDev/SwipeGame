package carletonstudentdev.googleplayservices;

public class TextObject
{
	
	private String text;

    private float xCoordiates,
                  yCoordiates,
                  textSize;

    private float[] boldness;
	
	public TextObject()
	{
        this.text = "DEFAULT";
        this.xCoordiates = 0f;
        this.yCoordiates = 0f;
        this.textSize = 1f;
        this.boldness = new float[] {1f, 1f, 1f, 1f};
	}
	
	public TextObject(String txt, float xCoordiates, float yCoordiates, float textSize)
	{
		this.text = txt;
		this.xCoordiates = xCoordiates;
		this.yCoordiates = yCoordiates;
        this.textSize = textSize;
        this.boldness = new float[] {1f, 1f, 1f, 1f};
	}

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public float getxCoordiates()
    {
        return xCoordiates;
    }

    public void setxCoordiates(float xCoordiates)
    {
        this.xCoordiates = xCoordiates;
    }

    public float getyCoordiates()
    {
        return yCoordiates;
    }

    public void setyCoordiates(float yCoordiates)
    {
        this.yCoordiates = yCoordiates;
    }

    public float getTextSize()
    {
        return textSize;
    }

    public void setTextSize(float textSize)
    {
        this.textSize = textSize;
    }

    public float[] getBoldness()
    {
        return boldness;
    }

    public void setBoldness(float[] boldness)
    {
        this.boldness = boldness;
    }
}
