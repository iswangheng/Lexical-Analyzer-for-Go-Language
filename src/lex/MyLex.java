package lex;

import javax.swing.UIManager;

public class MyLex 
{
	public static void main(String[] args)
	{
		try
		{
		     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		PaintGUI MyGUI = new PaintGUI();
		MyGUI.go();
	}
}
