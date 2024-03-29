
/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2017.
 * </p>
 * <p>
 * Generische Klasse List<ContentType>
 * </p>
 * <p>
 * Objekt der generischen Klasse List verwalten beliebig viele linear
 * angeordnete Objekte vom Typ ContentType. Auf hoechstens ein Listenobjekt,
 * aktuellesObjekt genannt, kann jeweils zugegriffen werden.<br />
 * Wenn eine Liste leer ist, vollstaendig durchlaufen wurde oder das aktuelle
 * Objekt am Ende der Liste geloescht wurde, gibt es kein aktuelles Objekt.<br />
 * Das erste oder das letzte Objekt einer Liste koennen durch einen Auftrag zum
 * aktuellen Objekt gemacht werden. Ausserdem kann das dem aktuellen Objekt
 * folgende Listenobjekt zum neuen aktuellen Objekt werden. <br />
 * Das aktuelle Objekt kann gelesen, veraendert oder geloescht werden. Ausserdem
 * kann vor dem aktuellen Objekt ein Listenobjekt eingefuegt werden.
 * </p>
 * 
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule, Materialien zum schulinternen Lehrplan Informatik SII
 * @version Generisch_03 2014-03-01
 */
public class List<ContentType> {

    /* --------- Anfang der privaten inneren Klasse -------------- */

    public class ListNode {

        private ContentType contentObject;
        private ListNode next;

        /**
         * Ein neues Objekt wird erschaffen. Der Verweis ist leer.
         * 
         * @param pContent das Inhaltsobjekt vom Typ ContentType
         */
        public ListNode(ContentType pContent) {
            contentObject = pContent;
            next = null;
        }

        /**
         * Der Inhalt des Knotens wird zurueckgeliefert.
         * 
         * @return das Inhaltsobjekt des Knotens
         */
        public ContentType getContentObject() {
            return contentObject;
        }

        /**
         * Der Inhalt dieses Kontens wird gesetzt.
         * 
         * @param pContent das Inhaltsobjekt vom Typ ContentType
         */
        public void setContentObject(ContentType pContent) {
            contentObject = pContent;
        }

        /**
         * Der Nachfolgeknoten wird zurueckgeliefert.
         * 
         * @return das Objekt, auf das der aktuelle Verweis zeigt
         */
        public ListNode getNextNode() {
            return this.next;
        }

        /**
         * Der Verweis wird auf das Objekt, das als Parameter uebergeben
         * wird, gesetzt.
         * 
         * @param pNext der Nachfolger des Knotens
         */
        public void setNextNode(ListNode pNext) {
            this.next = pNext;
        }

    }

    /* ----------- Ende der privaten inneren Klasse -------------- */

    // erstes Element der Liste
    public ListNode first;

    // letztes Element der Liste
    protected ListNode last;

    // aktuelles Element der Liste
    public ListNode current;

    /**
     * Eine leere Liste wird erzeugt.
     */
    public List() {
        first = null;
        last = null;
        current = null;

    }

    /**
     * Die Anfrage liefert den Wert true, wenn die Liste keine Objekte enthaelt,
     * sonst liefert sie den Wert false.
     * 
     * @return true, wenn die Liste leer ist, sonst false
     */
    public boolean isEmpty() {
        if (first == null){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Die Anfrage liefert den Wert true, wenn es ein aktuelles Objekt gibt,
     * sonst liefert sie den Wert false.
     * 
     * @return true, falls Zugriff moeglich, sonst false
     */
    public boolean hasAccess() {
        if(current == null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Falls die Liste nicht leer ist, es ein aktuelles Objekt gibt und dieses
     * nicht das letzte Objekt der Liste ist, wird das dem aktuellen Objekt in
     * der Liste folgende Objekt zum aktuellen Objekt, andernfalls gibt es nach
     * Ausfuehrung des Auftrags kein aktuelles Objekt, d.h. hasAccess() liefert
     * den Wert false.
     */
    public void next() {
        if(!isEmpty()&&hasAccess()){
            if (current.getNextNode()!=null){
                current = current.getNextNode();
            }
            else{ //am Ende der Liste kein aktuelles Objekt mehr vorhanden
                current = null;
            }
        }

    }

    /**
     * Falls die Liste nicht leer ist, wird das erste Objekt der Liste aktuelles
     * Objekt. Ist die Liste leer, geschieht nichts.
     */
    public void toFirst() {
        if(!isEmpty()){
            current = first;
        }

    }

    /**
     * Falls die Liste nicht leer ist, wird das letzte Objekt der Liste
     * aktuelles Objekt. Ist die Liste leer, geschieht nichts.
     */
    public void toLast() {
        if (!isEmpty()){
            current = last;
        }

    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird das
     * aktuelle Objekt zurueckgegeben, andernfalls (hasAccess() == false) gibt
     * die Anfrage den Wert null zurueck.
     * 
     * @return das aktuelle Objekt (vom Typ ContentType) oder null, wenn es
     *         kein aktuelles Objekt gibt
     */
    public ContentType getContent() {
        if (hasAccess()){
            return current.getContentObject();
        }
        else{
            return null;
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true) und pContent
     * ungleich null ist, wird das aktuelle Objekt durch pContent ersetzt. Sonst
     * geschieht nichts.
     * 
     * @param pContent
     *            das zu schreibende Objekt vom Typ ContentType
     */
    public void setContent(ContentType pContent) {
        if (hasAccess()){
            current.setContentObject(pContent);
        }

    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird ein neues
     * Objekt vor dem aktuellen Objekt in die Liste eingefuegt. Das aktuelle
     * Objekt bleibt unveraendert. <br />
     * Wenn die Liste leer ist, wird pContent in die Liste eingefuegt und es
     * gibt weiterhin kein aktuelles Objekt (hasAccess() == false). <br />
     * Falls es kein aktuelles Objekt gibt (hasAccess() == false) und die Liste
     * nicht leer ist oder pContent gleich null ist, geschieht nichts.
     * 
     * @param pContent
     *            das einzufuegende Objekt vom Typ ContentType
     */
    public void insert(ContentType pContent) {
        if (isEmpty()&& pContent!= null){
            append(pContent);
        }
        if (hasAccess()&& pContent != null){
            ListNode neu = new ListNode(pContent);
            ListNode vorgaenger = getPrevious(current);
            ListNode dahinter = current;

            //Einf�gen am Anfang der Liste
            if (vorgaenger == null){
                neu.setNextNode(first);
                first = neu;
            }
            else{
                neu.setNextNode(dahinter);
                vorgaenger.setNextNode(neu);
            }

        }

    }

    /**
     * Falls pContent gleich null ist, geschieht nichts.<br />
     * Ansonsten wird ein neues Objekt pContent am Ende der Liste eingefuegt.
     * Das aktuelle Objekt bleibt unveraendert. <br />
     * Wenn die Liste leer ist, wird das Objekt pContent in die Liste eingefuegt
     * und es gibt weiterhin kein aktuelles Objekt (hasAccess() == false).
     * 
     * @param pContent
     *            das anzuhaengende Objekt vom Typ ContentType
     */
    public void append(ContentType pContent) {

        if (pContent != null){
            ListNode neu = new ListNode(pContent);

            if (!isEmpty()){
                last.setNextNode(neu); 
                last = neu;
            }
            else{
                first = last = neu;
            }
        }

    }

    /**
     * Falls pList null oder eine leere Liste ist, geschieht nichts.<br />
     * Ansonsten wird die Liste pList an die aktuelle Liste angehaengt.
     * Anschliessend wird pList eine leere Liste. Das aktuelle Objekt bleibt
     * unveraendert. Insbesondere bleibt hasAccess identisch.
     * 
     * @param pList
     *            die am Ende anzuhaengende Liste vom Typ List<ContentType>
     */
    public void concat(List<ContentType> pList) {
        if (pList != null && !pList.isEmpty()) { // Nichts tun, wenn pList leer oder nicht existent.

            if (this.isEmpty()) { // Fall: An leere Liste anfuegen.
                this.first = pList.first;
                this.last = pList.last;
            } else { // Fall: An nicht-leere Liste anfuegen.
                this.last.setNextNode(pList.first);
                this.last = pList.last;
            }

            // Liste pList loeschen.
            pList.first = null;
            pList.last = null;
            pList.current = null;
        }
    }

    /**
     * Wenn die Liste leer ist oder es kein aktuelles Objekt gibt (hasAccess()
     * == false), geschieht nichts.<br />
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird das
     * aktuelle Objekt geloescht und das Objekt hinter dem geloeschten Objekt
     * wird zum aktuellen Objekt. <br />
     * Wird das Objekt, das am Ende der Liste steht, geloescht, gibt es kein
     * aktuelles Objekt mehr.
     */
    public void remove() {
        if(!isEmpty()&&hasAccess()){
            if(current != first && current != last){
                ListNode davor = getPrevious(current);
                ListNode dahinter = current.getNextNode();
                davor.setNextNode(dahinter);
                //das aktuelle Objekt auf den Knoten dahinter setzen
                current = dahinter;
                //das Element hat keinen Nachfolger steht am Ende und die Liste hat keinen Access mehr
                if (current.getNextNode() == null){
                    last = current;

                }
            }
            else if(current == first){
                first = current.getNextNode();
                current = first;
            }

            else if (current == last){
                last = getPrevious(current);
                //der Verweis auf den letzten Knoten muss noch rausgenommen werden
                last.setNextNode(null);
                current = null;
            }

            

        }
    }

    /**
     * Liefert den Vorgaengerknoten des Knotens pNode. Ist die Liste leer, pNode
     * == null, pNode nicht in der Liste oder pNode der erste Knoten der Liste,
     * wird null zurueckgegeben.
     *
     * @param pNode
     *         der Knoten, dessen Vorgaenger zurueckgegeben werden soll
     * @return der Vorgaenger des Knotens pNode oder null, falls die Liste leer ist,
     *         pNode == null ist, pNode nicht in der Liste ist oder pNode der erste Knoten
     *         der Liste ist
     */
    private ListNode getPrevious(ListNode pNode) {
        if (pNode == null || isEmpty() || pNode == first || findListNode(pNode)==null){
            return null;
        }
        else{
            //suche den Knoten und geben den Vorg�nger zur�ck
            ListNode cursor = first;
            ListNode previous = null;
            while(cursor != pNode){
                previous = cursor;
                cursor = cursor.getNextNode();

            }

            return previous;
        }

    }

    /**Suche einen Knoten in der Liste
    gibt null zur�ck, wenn der Knoten nicht in der Liste ist
    ansonsten wird der gesuchte Listenknoten zur�ckgegeben
    Kann ich so nicht verwenden, da ich nicht auf die einzelnen Knoten der Liste zugreifen kann.

     */
    public ListNode findListNode(ListNode pNode){
        if (pNode!=null){
            ListNode cursor = first;
            ListNode find = null;
            while(cursor != null && find == null){
                if(cursor == pNode){
                    find = cursor;
                }
                cursor = cursor.getNextNode();
            }

            return find;
        }
        else{
            return null;
        }
    }

}
