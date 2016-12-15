package client.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import client.control.CheckBoardListener;
import client.model.GameData;

public class CheckBoardCanvas extends Canvas
{
	private static final long serialVersionUID = 1L;

	private int WIDTH = 531;
	private int HEIGHT = 531;

	BufferedImage checkBoradImage = new BufferedImage(WIDTH, HEIGHT, 1);
	Graphics2D graphics2d = checkBoradImage.createGraphics();

	public CheckBoardCanvas()
	{
		drawBackGround();
		drawChess();
		this.setSize(WIDTH, HEIGHT);
		this.addMouseListener(new CheckBoardListener());
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(checkBoradImage, 0, 0, null);
	}

	public void drawBackGround()
	{
		graphics2d.setColor(new Color(80, 80, 80));;
		graphics2d.fillRect(0, 0, WIDTH, HEIGHT);
		graphics2d.setColor(Color.white);

		for (int i = 0; i < 15; i++)
		{
			graphics2d.drawLine((35 * i + 20), 20, (35 * i + 20), 510);
			graphics2d.drawLine(20, (35 * i + 20), 510, (35 * i + 20));
		}
	}

	public void drawChess()
	{
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
			{
				if (GameData.BLACK == GameData.getInstance().getChess(i, j))
				{
					graphics2d.setColor(Color.BLACK);
					graphics2d.fillOval(i * 35 + 4, j * 35 + 4, 33, 33);
				} else if (GameData.WHITE == GameData.getInstance().getChess(i, j))
				{
					graphics2d.setColor(Color.WHITE);
					graphics2d.fillOval(i * 35 + 4, j * 35 + 4, 33, 33);
				}
			}
	}
	
	public void update()
	{
		drawBackGround();
		drawChess();
		repaint();
	}

	public int getWidth()
	{
		return WIDTH;
	}

	public int getHeight()
	{
		return HEIGHT;
	}
}
