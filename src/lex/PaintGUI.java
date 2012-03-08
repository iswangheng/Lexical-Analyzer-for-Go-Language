package lex;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import java.awt.event.*;

public class PaintGUI 
{
	
	void go()
	{
		frame = new JFrame("Swarm's Lexical Analyser(For Go Language)"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocation(200, 80);
		
    	Container contentPane=frame.getContentPane();		
	    contentPane.setLayout(new BorderLayout());

		menuBar = new JMenuBar();
		menu_file = new JMenu("File");
		menu_view = new JMenu("View");
		menu_action = new JMenu("Action");
		menu_help = new JMenu("Help");

		myal = new MyAL();
		
		item_openFile = new JMenuItem("Open File");
		item_openFile.addActionListener(myal);
		item_saveFile = new JMenuItem("Save File");
		item_saveFile.addActionListener(myal);
		item_exit = new JMenuItem("Exit");
		item_exit.addActionListener(myal);

		item_comments = new JMenuItem("Comments");
		item_comments.addActionListener( myal);
		item_identifiers = new JMenuItem("Identifiers");
		item_identifiers.addActionListener( myal);
		item_keywords = new JMenuItem("Keywords");
		item_keywords.addActionListener(myal);
		item_operators = new JMenuItem("Operators");
		item_operators.addActionListener(myal);
		item_delimiters = new JMenuItem("Delimiters");
		item_delimiters.addActionListener(myal);
		item_integer = new JMenuItem("Integer");
		item_integer.addActionListener(myal);;
		item_floatingPoint = new JMenuItem("Floating Point");
		item_floatingPoint.addActionListener(myal);
		item_imaginary = new JMenuItem("Imaginary");
		item_imaginary.addActionListener(myal);
		item_character = new JMenuItem("Character");
		item_character.addActionListener(myal);
		item_string = new JMenuItem("String");
		item_string.addActionListener(myal);
		
		item_analyse = new JMenuItem("Analyse");
		item_analyse.addActionListener(myal);
		item_clearAll = new JMenuItem("Clear All");
		item_clearAll.addActionListener(myal);
		
		item_howTo = new JMenuItem("How To");
		item_howTo.addActionListener(myal);
		item_about = new JMenuItem("About");
		item_about.addActionListener(myal);
		
		menu_file.add(item_openFile);
		menu_file.add(item_saveFile);
		menu_file.addSeparator();
		menu_file.add(item_exit);
		
		menu_view.add(item_comments);
		menu_view.add(item_identifiers);
		menu_view.add(item_keywords);
		menu_view.add(item_operators);
		menu_view.add(item_delimiters);
		menu_view.add(item_integer);
		menu_view.add(item_floatingPoint);
		menu_view.add(item_imaginary);
		menu_view.add(item_character);
		menu_view.add(item_string);
		
		menu_action.add(item_analyse);
		menu_action.add(item_clearAll);
		
		menu_help.add(item_howTo);
		menu_help.add(item_about);
		
		menuBar.add(menu_file);
		menuBar.add(menu_view);
		menuBar.add(menu_action);
		menuBar.add(menu_help);
		
	    contentPane.add(menuBar,BorderLayout.NORTH);
		
	    centerPanel= new JPanel();
	    centerPanel.setVisible(true);
	    centerPanel.setLayout(new GridLayout(1,2));
	    sourcePanel = new JPanel();
	    sourcePanel.setVisible(true);
	    sourcePanel.setBorder(new TitledBorder("Source Code:"));
	    sourcePanel.setLayout(new BorderLayout());
	    sourceCodeArea = new JTextArea(	);
	    sourceCodeScroll = new JScrollPane(sourceCodeArea);
	    sourcePanel.add(sourceCodeScroll,BorderLayout.CENTER);
	    sourcePanel.add(new JLabel("  "),BorderLayout.WEST);
	    sourcePanel.add(new JLabel("  "),BorderLayout.EAST);
	    
	    outputPanel = new JPanel();
	    outputPanel.setVisible(true);
	    outputPanel.setLayout(new BorderLayout());
	    
	    resultPanel = new JPanel();
	    resultPanel.setVisible(true);
	    resultPanel.setLayout(new BorderLayout());
	    resultPanel.setBorder(new TitledBorder("Analysed Results:"));
	    
	    listModel1 = new DefaultListModel();
	    analysedResultList = new JList(listModel1);
	    analysedResultList.setVisibleRowCount(20);
	    analysedResultList.addMouseListener(new mouseAction());
	    analysedResultScroll = new JScrollPane(analysedResultList);
	    analysedResultScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    resultPanel.add(analysedResultScroll,BorderLayout.CENTER);
	    
	    errorsPanel = new JPanel();
	    errorsPanel.setVisible(true);
	    errorsPanel.setBorder(new TitledBorder("Errors:"));
	    errorsPanel.setLayout(new BorderLayout());
	    listModel2= new DefaultListModel();
	    errorsList= new JList(listModel2);
	    errorsList.setVisibleRowCount(4);
	    errorsList.addMouseListener(new mouseAction());
	    errorsScroll = new JScrollPane(errorsList);
	    errorsScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    errorsPanel.add(errorsScroll,BorderLayout.CENTER);   
	     
	    outputPanel.add(resultPanel,BorderLayout.CENTER);
	    outputPanel.add(errorsPanel,BorderLayout.SOUTH);	  
	      
	    centerPanel.add(sourcePanel);
	    centerPanel.add(outputPanel);
	    
	    contentPane.add(centerPanel,BorderLayout.CENTER);
	    frame.setVisible(true);
	}
	
	class mouseAction extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource() == analysedResultList)
			{
				if(e.getClickCount() == 2)
				{
					String temp = "";
					String currentLine = "";
					int index = 0;
					int line = 0;
					int startInt = 0;
					int endInt = 0;
					index = analysedResultList.locationToIndex(e.getPoint());
					currentLine = (String)listModel1.getElementAt(index);
					line = getLineNumber(currentLine) - 1;
					for(int i = 0; i < line; i++)
					{
						temp =  fullSourceArray.get(i);
						startInt += temp.length();
					}
					System.out.println("Line: "+line+'\n'+"startInt: "+startInt);
					endInt = startInt + fullSourceArray.get(line).length() -1; 
					System.out.println("endInt: "+endInt);
					sourceCodeArea.setCaretPosition(startInt);
					sourceCodeArea.moveCaretPosition(endInt);
					sourceCodeArea.requestFocus();
				}
			}
			if(e.getSource() == errorsList)
			{
				if(e.getClickCount() == 2)
				{
					String temp = "";
					String currentLine = "";
					int index = 0;
					int line = 0;
					int startInt = 0;
					int endInt = 0;
					index = errorsList.locationToIndex(e.getPoint());
					currentLine = (String)listModel2.getElementAt(index);
					line = getLineNumber(currentLine) - 1;
					for(int i = 0; i < line; i++)
					{
						temp =  fullSourceArray.get(i);
						startInt += temp.length();
					}
					System.out.println("Line: "+line+'\n'+"startInt: "+startInt);
					endInt = startInt + fullSourceArray.get(line).length() -1; 
					System.out.println("endInt: "+endInt);
					sourceCodeArea.setCaretPosition(startInt);
					sourceCodeArea.moveCaretPosition(endInt);
					sourceCodeArea.requestFocus();
				}
			}
		}
	}
	
	void openFile() 
	{
		sourceCodeArea.setText("");
		BufferedReader in = null;
		JFrame dialogFrame = new JFrame();
		StringBuffer sbtemp = new StringBuffer("");
		String temp = null;
		int index = 1;
		filechooser = new JFileChooser();
		ExampleFileFilter filter = new ExampleFileFilter();
		filter.addExtension("go");
		filter.setDescription("go source file");
		filechooser.setFileFilter(filter);
		int v = filechooser.showOpenDialog(dialogFrame);
		if (v == JFileChooser.APPROVE_OPTION) {
			filePath = filechooser.getSelectedFile().getPath();
			try {
				in = new BufferedReader(new FileReader(filePath));
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(frame, "Can not open the file!", "Open File Error", JOptionPane.ERROR_MESSAGE);
			}
			try {
				while ((temp = in.readLine()) != null) {
					String tempSource = "";
					temp += '\n';
					tempSource = index +" "+temp;
					sbtemp.append(tempSource);
					sourceArray.add(temp);
					fullSourceArray.add(tempSource);
					index++;
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
			sourceCodeArea.append(sbtemp.toString());
		}
	}
	
	boolean isKeyword(String testString)		//判断标识符是否为关键字
	{
		int i = 0;
		boolean flag = false;
		for(i = 0 ; i < keywords.length;i++)
		{
			if(testString.equals(keywords[i]))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	int getLineNumber(String lineString)
	{
		int i = 6;
		int lineNumber = 0;
		String iLine = "";
		while(lineString.charAt(i) != ' ')
		{
			iLine += lineString.charAt(i);
			i++;
		}
		lineNumber = Integer.valueOf(iLine);
		return lineNumber;
	}
	
	void analyse()
	{
		int i = 0;		//i用来表示行
		int j = 0;		//j用来指向行中的某一位
		int line = 0; //line用来表示行号
		int status = 0;
		char tchar;
		listModel1.clear();
		listModel2.clear();
		for(i = 0; i < sourceArray.size(); i++)
		{
			line = i + 1;
			j = 0;
			do
			{
				tchar = sourceArray.get(i).charAt(j);
				switch(status)
				{
				case 0:
					if('/' == tchar)
					{
						status = 1;
					}
					else if('_' == tchar || (tchar >= 'a' && tchar <= 'z') || (tchar >='A' && tchar <= 'Z'))
					{
						analysedWord += tchar;
						status = 7;
					}
					else if(tchar <= '9' && tchar >= '1')
					{
						analysedWord += tchar;
						status = 8;
					}
					else if(tchar == '0')
					{
						analysedWord += tchar;
						status = 10;
					}
					else if(tchar == '.')
					{
						analysedWord += tchar;
						status = 16;
					}
					else if(tchar == '`')
					{
						analysedWord += tchar;
						status = 18;
					}
					else if(tchar == '"')
					{
						analysedWord += tchar;
						status = 23;
					}
					else if(tchar == ';')
					{
						analysedWord += tchar;
						status = 24;
					}
					else if(tchar == ')' || tchar == '(')
					{
						analysedWord += tchar;
						status = 25;
					}
					else if(tchar == ']' || tchar =='[')
					{
						analysedWord += tchar;
						status = 26;
					}
					else if(tchar == '}' || tchar == '{')
					{
						analysedWord += tchar;
						status = 27;
					}
					else if(tchar == ',')
					{
						analysedWord += tchar;
						status = 28;
					}
					else if(tchar == ':')
					{
						analysedWord += tchar;
						status = 29;
					}
					else if(tchar == '+')
					{
						analysedWord += tchar;
						status = 30;
					}
					else if(tchar == '-')
					{
						analysedWord += tchar;
						status = 33;
					}
					else if(tchar == '*')
					{
						analysedWord += tchar;
						status = 36;
					}
					else if(tchar == '%')
					{
						analysedWord += tchar;
						status = 38;
					}
					else if(tchar == '&')
					{
						analysedWord += tchar;
						status = 41;
					}
					else if(tchar == '|')
					{
						analysedWord += tchar;
						status = 48;
					}
					else if(tchar == '^')
					{
						analysedWord += tchar;
						status = 51;
					}
					else if(tchar == '<')
					{
						analysedWord += tchar;
						status = 53;
					}
					else if(tchar == '>')
					{
						analysedWord += tchar;
						status = 58;
					}
					else if(tchar == '=')
					{
						analysedWord += tchar;
						status = 62;
					}
					else if(tchar == '!')
					{
						analysedWord += tchar;
						status = 64;
					}
					else if(tchar == '\'')
					{
						analysedWord += tchar;
						status = 66;
					}
					else if(tchar == ' ' || tchar == '\n')
					{
						status = 0;
					}
					j++;
					break;
				case 1:
					if('/' == tchar)
					{
						status = 2;
					}
					else if('*' == tchar)
							{
								status = 4;
							}
					else if('=' == tchar)
							{
								status = 47;
							}
					else
					{
						resultstring = "Line: "+i+blank+"Operators"+blank+"'/')";
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						status = 0;
					}		
					j++;
					break;
				case 2:
					if(tchar != '\n')
					{
						analysedWord += tchar;
						status = 2;
						j++;
					}
					else
					{
						status = 3;
					}
					break;
				case 3:
					resultstring = "Line: "+line+blank+"Comments"+blank+analysedWord;
					listModel1.addElement(resultstring);
					commentsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					j++;
					break;
				case 4:
					if(tchar == '*')
					{
						status = 5;
					}
					else
					{
						analysedWord += tchar;
						status = 4;
					}
					j++;
					break;
				case 5:
					if(tchar == '/')
					{
						status = 6;
					}
					else
					{
						status = 100;
					}
					break;
				case 6:
					resultstring = "Line: "+line+blank+"Comments"+blank+analysedWord;
					listModel1.addElement(resultstring);
					commentsArray.add(resultstring);
					analysedWord = "";
					j++;
					status = 0;
					break;
				case 7:
					if((tchar >= 'a' && tchar <= 'z') || (tchar >= '0' && tchar <= '9')|| (tchar >='A' && tchar <= 'Z'))
					{
						analysedWord += tchar;
						status = 7;
						j++;
					}
					else  
					{
						if(isKeyword(analysedWord))
						{
							resultstring = "Line: "+line+blank+"Keywords"+blank+analysedWord;
							listModel1.addElement(resultstring);
							keywordsArray.add(resultstring);
							analysedWord = "";
						}
						else
						{
							resultstring = "Line: "+line+blank+"Identifiers"+blank+analysedWord;
							listModel1.addElement(resultstring);
							identifiersArray.add(resultstring);
							analysedWord = "";
						}
						status = 0;
					}
					break;
				case 8:
					if(tchar == '.')
					{
						analysedWord += tchar;
						status = 13;
						j++;
					}
					else if(tchar == 'i')
					{
						analysedWord += tchar;
						status = 9;
						j++;
					}
					else if(tchar >= '0' && tchar <= '9')
					{
						analysedWord += tchar;
						status = 8;
						j++;
					}
					else if(tchar =='e' || tchar == 'E')
					{
						analysedWord += tchar;
						status = 14;
						j++;
					}
					else if((tchar >= 'a' && tchar <= 'd') || (tchar >= 'f' && tchar <= 'z')
						|| (tchar >= 'A' && tchar <= 'D')
						|| (tchar >='F' && tchar <= 'Z') )
					{
						analysedWord += tchar;
						status = 100;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Integers"+blank+analysedWord;
						listModel1.addElement(resultstring);
						integerArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 9:
					if((tchar >= 'a' && tchar <= 'z') || (tchar >= 'A' && tchar <= 'Z'))
					{
						analysedWord += tchar;
						status = 100;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Imaginary"+blank+analysedWord;
						listModel1.addElement(resultstring);
						imaginaryArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 10:
					if(tchar == 'x')
					{
						analysedWord += tchar;
						j++;
						status = 11;
					}
					else if(tchar >= '0' && tchar <= '7')
					{
						analysedWord += tchar;
						j++;
						status = 47;
					}
					else if(tchar == '8' || tchar == '9')
					{
						analysedWord += tchar;
						j++;
						status = 15;
					}
					else if(tchar == '.')
					{
						analysedWord += tchar;
						j++;
						status = 13;
					}
					else if((tchar >= 'a' && tchar <= 'w') || (tchar >= 'A' && tchar <= 'W')
							|| (tchar >= 'y' && tchar <= 'z') || (tchar >= 'Y' && tchar <= 'Z') )
					{
						analysedWord += tchar;
						status = 100;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Integers"+blank+analysedWord;
						listModel1.addElement(resultstring);
						integerArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 11:
					if( (tchar >= '0' && tchar <='9') || (tchar >= 'a' && tchar <= 'f') || (tchar>= 'A' && tchar <= 'F') )
					{
						analysedWord += tchar;
						j++;
						status = 12;
					}
					else 
					{
						analysedWord += tchar;
						status = 100;
					}
					break;
				case 12:
					if( (tchar >= '0' && tchar <='9') || (tchar >= 'a' && tchar <= 'f') || (tchar>= 'A' && tchar <= 'F') )
					{
						analysedWord += tchar;
						j++;
						status = 12;
					}
					else if( (tchar >= 'g' && tchar <= 'z') || (tchar>= 'G' && tchar <= 'Z') )
					{
						analysedWord += tchar;
						status = 100;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Integers"+blank+analysedWord;
						listModel1.addElement(resultstring);
						integerArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 13:
					if(tchar == 'e' || tchar == 'E')
					{
						analysedWord += tchar;
						j++;
						status = 14;
					}
					else if(tchar >= '0' && tchar <= '9')
					{
						analysedWord += tchar;
						j++;
						status = 13;
					}
					else if(tchar == 'i')
					{
						analysedWord += tchar;
						j++;
						status = 9;
					}
					else if((tchar >= 'a' && tchar <= 'd') || (tchar >= 'A' && tchar <= 'D')
							|| (tchar >= 'f' && tchar <= 'h') || (tchar >='F' && tchar <= 'H')
							|| (tchar >= 'j' && tchar <= 'z') || (tchar >= 'J') && tchar <= 'Z')
					{
						analysedWord += tchar;
						status = 100;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Floating Point"+blank+analysedWord;
						listModel1.addElement(resultstring);
						floatingPointArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 14:
					if(tchar == '+' || tchar == '-')
					{
						analysedWord += tchar;
						j++;
						status = 69;
					}
					else if(tchar >= '0' && tchar <= '9')
					{
						analysedWord += tchar;
						j++;
						status = 13;
					}
					else
					{
						analysedWord += tchar;
						status = 100;
					}
					break;
				case 15:
					if(tchar >= '0' && tchar <= '9')
					{
						analysedWord += tchar;
						j++;
						status = 15;
					}
					else if(tchar == '.')
					{
						analysedWord += tchar;
						j++;
						status = 13;
					}
					else if(tchar == 'e' || tchar == 'E')
					{
						analysedWord += tchar;
						j++;
						status = 14;
					}
					else if((tchar >= 'a' && tchar <= 'd') || (tchar >= 'f' && tchar <= 'z')
							|| (tchar >= 'A' && tchar <= 'D') || (tchar >='F' && tchar <= 'Z') )
						{
							analysedWord += tchar;
							status = 100;
						}
					else
					{
						resultstring = "Line: "+line+blank+"Integers"+blank+analysedWord;
						listModel1.addElement(resultstring);
						integerArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 16:
					if(tchar >= '0' && tchar <= '9')
					{
						analysedWord += tchar;
						j++;
						status = 17;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Delimiters"+blank+analysedWord;
						listModel1.addElement(resultstring);
						delimitersArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 17:
					if(tchar >= '0' && tchar <= '9')
					{
						analysedWord += tchar;
						j++;
						status = 17;
					}
					else if(tchar == 'e' || tchar == 'E')
					{
						analysedWord += tchar;
						j++;
						status = 14;
					}
					else if(tchar == 'i')
					{
						analysedWord += tchar;
						j++;
						status = 9;
					}
					else if((tchar >= 'a' && tchar <= 'd') || (tchar >= 'A' && tchar <= 'D')
							|| (tchar >= 'f' && tchar <= 'h') || (tchar >='F' && tchar <= 'H')
							|| (tchar >= 'j' && tchar <= 'z') || (tchar >= 'J') && tchar <= 'Z')
					{
						analysedWord += tchar;
						status = 100;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Floating Point"+blank+analysedWord;
						listModel1.addElement(resultstring);
						floatingPointArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 18:
					analysedWord += tchar;
					status = 19;
					j++;
					break;
				case 19:
					if(tchar == '`')
					{
						analysedWord += tchar;
						status = 20;
					}
					else
					{
						analysedWord += tchar;
						status = 21;
					}
					j++;
					break;
				case 20:
					resultstring = "Line: "+line+blank+"Char"+blank+analysedWord;
					listModel1.addElement(resultstring);
					characterArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 21:
					if(tchar == '`')
					{
						analysedWord += tchar;
						status = 22;
					}
					else
					{
						analysedWord += tchar;
						status = 21;
					}
					j++;
					break;
				case 22:
					resultstring = "Line: "+line+blank+"String"+blank+analysedWord;
					listModel1.addElement(resultstring);
					stringArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 23:
					if(tchar == '"')
					{
						analysedWord += tchar;
						status = 22;
					}
					else
					{
						analysedWord += tchar;
						status = 23;
					}
					j++;
					break;
				case 24:
				case 25:
				case 26:
				case 27:
				case 28:
				case 29:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 40;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Delimiters"+blank+analysedWord;
						listModel1.addElement(resultstring);
						delimitersArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 30:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 31;
						j++;
					}
					else if(sourceArray.get(i).charAt(j) == '+')
					{
						analysedWord += tchar;
						status = 32;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 31:
				case 32: 
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 33:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 34;
						j++;
					}
					else if(sourceArray.get(i).charAt(j) == '-')
					{
						analysedWord += tchar;
						status = 35;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 34:					
				case 35: 
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 36:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 37;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 37: 
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 38:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 39;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 39: 
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 40: 
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 41:
					if(sourceArray.get(i).charAt(j) == '^')
					{
						analysedWord += tchar;
						status = 45;
						j++;
					}
					else if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 43;
						j++;
					}
					else if(sourceArray.get(i).charAt(j) == '&')
					{
						analysedWord += tchar;
						status = 44;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 42:
				case 43:
				case 44: 
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 45:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 46;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 46: 
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 47:
					if(tchar == '.')
					{
						analysedWord += tchar;
						j++;
						status = 13;
					}
					else if(tchar >= '0' && tchar <= '7')
					{
						analysedWord += tchar;
						j++;
						status = 47;
					}
					else if(tchar == '8' || tchar == '9')
					{
						analysedWord += tchar;
						j++;
						status = 15;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Integers"+blank+analysedWord;
						listModel1.addElement(resultstring);
						integerArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 48:
					if(sourceArray.get(i).charAt(j) == '|')
					{
						analysedWord += tchar;
						status = 49;
						j++;
					}
					else if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 50;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 49:
				case 50:
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 51:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 52;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 52:
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 53:
					if(sourceArray.get(i).charAt(j) == '<')
					{
						analysedWord += tchar;
						status = 54;
						j++;
					}
					else if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 56;
						j++;
					}
					else if(sourceArray.get(i).charAt(j) == '-')
					{
						analysedWord += tchar;
						status = 57;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 54:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 55;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 55:
				case 56:
				case 57:
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 58:
					if(sourceArray.get(i).charAt(j) == '>')
					{
						analysedWord += tchar;
						status = 59;
						j++;
					}
					else if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 61;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 59:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 60;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 60:
				case 61:
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 62:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 63;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 63:
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 64:
					if(sourceArray.get(i).charAt(j) == '=')
					{
						analysedWord += tchar;
						status = 65;
						j++;
					}
					else
					{
						resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
						listModel1.addElement(resultstring);
						operatorsArray.add(resultstring);
						analysedWord = "";
						status = 0;
					}
					break;
				case 65:
					resultstring = "Line: "+line+blank+"Operators"+blank+analysedWord;
					listModel1.addElement(resultstring);
					operatorsArray.add(resultstring);
					analysedWord = "";
					status = 0;
					break;
				case 66:
					analysedWord += tchar;
					status = 67;
					j++;
					break;
				case 67:
					if(tchar == '\'')
					{
						analysedWord += tchar;
						j++;
						status = 68;
					}
					else
					{
						analysedWord += tchar;
						status = 100;
					}
					break;
				case 68:
					resultstring = "Line: "+line+blank+"Character"+blank+analysedWord;
					listModel1.addElement(resultstring);
					characterArray.add(resultstring);
					status = 0;
					analysedWord = "";
					break;
				case 69:
					if(tchar >= '0' && tchar <= '9')
					{
						analysedWord += tchar;
						j++;
						status = 13;
					}
					else
					{
						analysedWord += tchar;
						status = 100;
					}
					break;
					//case 100为出错处理！
				case 100:
					resultstring = "Line: "+line+blank+"error: "+analysedWord;
					listModel2.addElement(resultstring);
					analysedWord = "";
					j++;
					status = 0;
					break;
				default:
					status = 0;
					break;
				}
			}
			while(j < sourceArray.get(i).length());
		}
	}
	
	void showComments()
	{
		listModel1.clear();
		int i = 0;
		for(i = 0; i <commentsArray.size(); i++)
		{
			listModel1.addElement(commentsArray.get(i));
		}
	}
	
	void showIdentifiers()
	{
		listModel1.clear();
		int i = 0;
		for(i = 0; i < identifiersArray.size(); i++)
		{
			listModel1.addElement(identifiersArray.get(i));
		}
	}
	
	void showKeywords()
	{
		listModel1.clear();
		int i = 0;
		for(i = 0; i < keywordsArray.size(); i++)
		{
			listModel1.addElement(keywordsArray.get(i));
		}
	}
	

	void showOperators()
	{
		listModel1.clear();
		int i = 0;
		for(i = 0; i < operatorsArray.size(); i++)
		{
			listModel1.addElement(operatorsArray.get(i));
		}
	}
	
   void showDelimiters()
   {
		listModel1.clear();
		int i = 0;
		for(i = 0; i < delimitersArray.size(); i++)
		{
			listModel1.addElement(delimitersArray.get(i));
		}
   }
   
   void showIntegers()
   {
		listModel1.clear();
		int i = 0;
		for(i = 0; i < integerArray.size(); i++)
		{
			listModel1.addElement(integerArray.get(i));
		}
   }
  
   void showFloatingPoint()
   {
		listModel1.clear();
		int i = 0;
		for(i = 0; i < floatingPointArray.size(); i++)
		{
			listModel1.addElement(floatingPointArray.get(i));
		}
   }
   
   void showImaginary()
   {
		listModel1.clear();
		int i = 0;
		for(i = 0; i < imaginaryArray.size(); i++)
		{
			listModel1.addElement(imaginaryArray.get(i));
		}
   } 
   
   void showCharacter()
   {
		listModel1.clear();
		int i = 0;
		for(i = 0; i < characterArray.size(); i++)
		{
			listModel1.addElement(characterArray.get(i));
		}
   }

   void showString()
   {
		listModel1.clear();
		int i = 0;
		for(i = 0; i < stringArray.size(); i++)
		{
			listModel1.addElement(stringArray.get(i));
		}
   }
	
	void saveFile()
	{
		JFrame dialogFrame = new JFrame();
		StringBuffer filePath = new StringBuffer("");
		JFileChooser c = new JFileChooser();
		ExampleFileFilter filter = new ExampleFileFilter();
		filter.addExtension("txt");
		filter.setDescription("analysed results");
		c.setFileFilter(filter);
		int v = c.showSaveDialog(dialogFrame);
		if (v == JFileChooser.APPROVE_OPTION) {
			filePath.append(c.getSelectedFile().getPath());
		}
		if ((filePath.lastIndexOf(".")) == -1) {
			filePath.append(".txt");
		}
		try {
			BufferedWriter out = null;
			out = new BufferedWriter(new FileWriter(filePath.toString()));
			out.write("Analysed Results:\n");
			for(int i = 0; i < listModel1.size(); i++)
			{
				out.write(listModel1.elementAt(i).toString()+'\n');
			}
			out.write("\n\nErrors:\n");
			for(int i = 0; i < listModel2.size(); i++)
			{
				out.write(listModel2.elementAt(i).toString()+'\n');
			}
			out.close();
		} catch (IOException e2) {
		}
	}
	
	class MyAL implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String actionCommand = event.getActionCommand();
			if(actionCommand == "Exit")
				System.exit(0);
			if(actionCommand == "Open File")
			{
				openFile();
			}
			if(actionCommand == "Save File")
			{
				saveFile();
			}
			if(actionCommand == "Comments")
			{
				showComments();
			}
			if(actionCommand == "Identifiers")
			{
				showIdentifiers();
			}
			if(actionCommand == "Keywords")
			{
				showKeywords();
			}
			if(actionCommand == "Operators")
			{
				showOperators();
			}
			if(actionCommand == "Delimiters")
			{
				showDelimiters();
			}
			if(actionCommand == "Integer")
			{
				showIntegers();
			}
			if(actionCommand == "Floating Point")
			{
				showFloatingPoint();
			}
			if(actionCommand == "Imaginary")
			{
				showImaginary();
			}
			if(actionCommand == "Character")
			{
				showCharacter();
			}
			if(actionCommand == "String")
			{
				showString();
			}
			if(actionCommand == "Analyse")
			{
				analyse();
			}
			if(actionCommand == "Clear All")
			{
				sourceCodeArea.setText(null);
				listModel1.clear();
				listModel2.clear();
			}
			if(actionCommand == "How To")
			{
				String message = "1. Click 'File' -> 'Open File' to open a source code\n" +
				"2. Click 'Action' -> 'Analyse' to analyse the souce code\n"  +
				"3. Click 'View' to see the results clearly\n" +
				"4. Click 'File' -> 'Save File' to save the results\n" +
				"5. Thanks for using!";
				JOptionPane.showMessageDialog(frame, message, "How To Use This", JOptionPane.QUESTION_MESSAGE);
			}
			if(actionCommand == "About")
			{
				String message = "This simple Lexical Analyser is made by Swarm!\n" +
						"       Version: 0.9 \n" +
						"       Student No: 0706550128\n"  +
						"       Student Name: 王恒 \n" +
						"CopyRight (c) Swarm and NUST.All rights reserved!";
				JOptionPane.showMessageDialog(frame, message, "About this", JOptionPane.INFORMATION_MESSAGE);
			}				
		}
	}
	
	JFrame frame;
	
	//菜单栏
	JMenuBar menuBar;
	
	//菜单
	JMenu menu_file;
	JMenu menu_view;
	JMenu menu_action;
	JMenu menu_help;
	
	//menu_file的子菜单
	JMenuItem item_openFile;
	JMenuItem item_saveFile;
	JMenuItem item_exit;
	
	//menu_view的子菜单
	JMenuItem item_comments;
	JMenuItem item_identifiers;
	JMenuItem item_keywords;
	JMenuItem item_operators;
	JMenuItem item_delimiters;
	JMenuItem item_integer;
	JMenuItem item_floatingPoint;
	JMenuItem item_imaginary;
	JMenuItem item_character;
	JMenuItem item_string;
	
	//menu_action的子菜单
	JMenuItem item_analyse;
	JMenuItem item_clearAll;
	
	//menu_help的子菜单
	JMenuItem item_howTo;
	JMenuItem item_about;
	
	//MyAL为处理事件的类
	MyAL myal;
	
	//中间的Panel，存放左边的sourcePanel和右边的outputPanel
 	JPanel centerPanel;
 	//sourePanel里放有sourceCodeArea，即源代码
 	JPanel sourcePanel;
 	//resultPanel里放有analysedResultList，即分析结果
 	JPanel resultPanel;
 	//errorsPanel里放有errorsList，即错误结果
 	JPanel errorsPanel;
 	//outputPanel里放有resultPanel在上面，errorsPanel在下面
 	JPanel outputPanel;
	
 	JScrollPane sourceCodeScroll;
 	JScrollPane analysedResultScroll;
 	JScrollPane errorsScroll;
 	
 	JTextArea sourceCodeArea;
 	JList analysedResultList;
 	JList errorsList;
 	DefaultListModel listModel1=null;
 	DefaultListModel listModel2=null;

 	String blank = "            ";	
 	
 	JFileChooser filechooser;
 	String filePath;				//打开文件的file path
 	
 	String keywords[] = {"break","case","chan","const","continue","default","defer","else",
 			"fallthrough","for","func","go","goto","if","import","interface","map","package",
 			"range","return","select","struct","switch","type","var","main","true","false","byte"};
 	
 	String analysedWord="";			//存储分析的词语
 	
 	ArrayList<String> fullSourceArray = new ArrayList<String>();	
 	ArrayList<String> sourceArray = new ArrayList<String>();	//定义存储source code的string数组,每一行为一个string元素
 	String resultstring;	//定义存储分析结果的string（单个结果）
 	ArrayList<String> resultArray = new ArrayList<String>();  //定义存储分析结果的数组
	ArrayList<String> commentsArray = new ArrayList<String>();  //定义comments结果的数组
	ArrayList<String> identifiersArray = new ArrayList<String>();  //定义identifiers结果的数组
	ArrayList<String> keywordsArray = new ArrayList<String>();  //定义keywords结果的数组
	ArrayList<String> operatorsArray = new ArrayList<String>();  //定义operators结果的数组
	ArrayList<String> delimitersArray = new ArrayList<String>();  //定义delimiters结果的数组
	ArrayList<String> integerArray = new ArrayList<String>();  //定义integer结果的数组
	ArrayList<String> floatingPointArray = new ArrayList<String>();  //定义floatingPoint结果的数组
	ArrayList<String> imaginaryArray = new ArrayList<String>();  //定义imaginary结果的数组
	ArrayList<String> characterArray = new ArrayList<String>();  //定义character结果的数组
	ArrayList<String> stringArray = new ArrayList<String>();  //定义string结果的数组
}
