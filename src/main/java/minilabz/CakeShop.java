package minilabz;

import consoleUI.ConsoleMethods;

public class CakeShop extends Generic {
        public enum KeyType {title, caketype, frosting}
        public static minilabz.CakeShop.KeyType key = minilabz.CakeShop.KeyType.title;
        public String caketype;
        public boolean frosting;

        CakeShop(String caketype, boolean frosting)
        {
            this.setType("Cake");
            this.caketype = caketype;
            this.frosting = frosting;
        }

        public String toString() {
            String output="";
            switch (key) {
                case caketype:
                    output += this.caketype;
                    break;
                case frosting:
                    output += this.frosting;
                    break;
                case title:
                default:
                    output = super.getType() + ": " + this.caketype + ", " + this.frosting;
            }

            return output;
        }

        public static Generic[] cakes () {
            return new Generic[]{
                    new minilabz.CakeShop("Boston Cream Cake", true),
                    new minilabz.CakeShop("Boston Cream Cake", false),
                    new minilabz.CakeShop("Ice Cream Cake", true),
                    new minilabz.CakeShop("Ice Cream Cake", false),
                    new minilabz.CakeShop("Strawberry Shortcake", true),
                    new minilabz.CakeShop("Strawberry Shortcake", false),
                    new minilabz.CakeShop("Black Forest Cake", true),
                    new minilabz.CakeShop("Black Forest Cake", false),
            };
        }

        public static void main(String[] args)
        {
            Generic[] CakeShop = cakes();
            for(Generic c:  CakeShop)
                ConsoleMethods.println(c);
        }
}
