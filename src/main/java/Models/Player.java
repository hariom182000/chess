package Models;

import enums.Colours;
import lombok.Data;

import java.util.Set;

@Data
public class Player {

    private String playerName;

    private Colours colour;

    private Set<Piece> pieces;
}