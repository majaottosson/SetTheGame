package SetLogic;

import java.util.Objects;

public class Cards {
	String color;
	int number;
	char figure;
	String filled;
	
	public Cards(String color, int number, char figure, String filled) {
		this.color = color;
		this.number = number;
		this.figure = figure;
		this.filled = filled;
		
	}
	
	public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public char getFigure() {
        return figure;
    }
    
    public String getFilled() {
    	return filled;
    }
	
    @Override
	public boolean equals(Object other) {
		if (other instanceof Cards) {
			return color.equalsIgnoreCase(((Cards) other ).color) 
					&& number == ((Cards) other).number
					&& figure == ((Cards) other).figure
					&& filled.equalsIgnoreCase(((Cards) other).filled);
		} else return false;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(color, number, figure, filled);
    }
}
