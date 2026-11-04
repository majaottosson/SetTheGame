package Swing;



import javax.swing.*;

import SetLogic.Cards;

import java.awt.*;

public class CardComponent extends JPanel {
    private final Cards card;

    public CardComponent(Cards card) {
        this.card = card;
        setPreferredSize(new Dimension(100, 150));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        
        Color drawColor = switch (card.getColor()) {
            case "red" -> Color.RED;
            case "green" -> Color.GREEN;
            case "blue" -> Color.BLUE;
            default -> Color.BLACK;
        };
        g2.setColor(drawColor);

        int shapeCount = card.getNumber();
        int shapeHeight = 30;
        int shapeSpacing = 10;
        int startY = (getHeight() - (shapeCount * shapeHeight + (shapeCount - 1) * shapeSpacing)) / 2;

        for (int i = 0; i < shapeCount; i++) {
            int y = startY + i * (shapeHeight + shapeSpacing);
            drawShape(g2, 20, y, 60, shapeHeight);
        }
    }

    private void drawShape(Graphics2D g2, int x, int y, int w, int h) {
        Shape shape;
        switch (card.getFigure()) {
            case 'O' -> shape = new java.awt.geom.Ellipse2D.Float(x, y, w, h);  
            case '<' -> {
                Polygon diamond = new Polygon();
                diamond.addPoint(x + w / 2, y);
                diamond.addPoint(x + w, y + h / 2);
                diamond.addPoint(x + w / 2, y + h);
                diamond.addPoint(x, y + h / 2);
                shape = diamond;
            }
            case 'S' -> shape = new Rectangle(x, y, w, h);  
            default -> shape = new Rectangle(x, y, w, h);
        }

        switch (card.getFilled()) {
            case "empty" -> g2.draw(shape);
            case "full" -> g2.fill(shape);
            case "striped" -> {
                g2.draw(shape);
                Rectangle bounds = shape.getBounds();
                for (int i = bounds.x; i < bounds.x + bounds.width; i += 5) {
                    g2.drawLine(i, bounds.y, i, bounds.y + bounds.height);
                }
            }
        }
    }
}