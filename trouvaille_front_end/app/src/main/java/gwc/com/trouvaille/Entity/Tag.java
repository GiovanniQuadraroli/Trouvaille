package gwc.com.trouvaille.Entity;

public class Tag {

    public static enum Tags{
        APERITIVO("aperitivo"),
        BAR("bar"),
        BOARDGAMES("board games"),
        CAFE("cafe"),
        CASUALDRINKING("casual drinking"),
        CHINESECUISINE("chinese cuisine"),
        CLUB("club"),
        COSYBAR("cosy bar"),
        ELECTRONICMUSIC("electronic music");


        private String tag;

        private Tags(String s){tag = s;}
    }

    public Tag(Tags tag){
        this.tag = tag;
    }

    private Tags tag;

    private String image;

    public String toString(){
        return tag.toString();
    }
}
