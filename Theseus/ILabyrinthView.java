
/**
 * Interface für Sichten des Labyrinths
 */

public interface ILabyrinthView
{
    // Das gesamte Labyrinth wird angezeigt, gezeichnet, ausgegeben, ...
    void zeigeAlles();
    
    // Ein Feld des Labyrinths wird angezeigt, gezeichnet, ausgegeben, ...
    void zeige(int x, int y);
}
