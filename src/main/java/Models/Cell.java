package Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cell {
    private Integer xPos;

    private Integer yPos;

    public Boolean isValidCell(final Integer boardSize) {
        if (xPos < 0 || yPos < 0 || xPos >= boardSize || yPos >= boardSize)
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

}

