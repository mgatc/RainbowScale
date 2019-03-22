package rainbowscaledriver;

import java.math.BigDecimal;

public class RainbowScale {
    protected SingleColorDistribution red;
    protected SingleColorDistribution green;
    protected SingleColorDistribution blue;
    // Used to convert between unit intervals and the 0-255 RGB scale
    protected Scale scale = new Scale( new BigDecimal( 255 ) );
    
    // The constructor just initializes the color distributions, which
    // actually do the math
    public RainbowScale() {
       this.red = new SingleColorDistribution( SingleColorDistribution.RED );
       this.green = new SingleColorDistribution( SingleColorDistribution.GREEN);
       this.blue = new SingleColorDistribution( SingleColorDistribution.BLUE );
    }
    // given a valid UnitInterval, 
    // return the associated RGB values as an int array
    public int[] color( UnitInterval index ) {
        int[] color = new int[3]; // output
        UnitInterval[] uiColor = {      // get the RGB values (in UI)
            this.red.getValue( index ),
            this.green.getValue( index ) ,
            this.blue.getValue( index ) 
        };
        // Convert each UI value to 255 scale
        for( int i=0; i<uiColor.length; i++ ) { 
            color[i] = this.scale.getValue( uiColor[i] ).intValue();
        }
        return color;
    }
    // Converts the double index into a UI, then call the other color function
    public int[] color( double index ) {
        UnitInterval ui = new UnitInterval( index );
        return this.color( ui );
    }
    // Return the rgb color in a hex string
    public String hexColor( UnitInterval index ) {
        int[] rgb = this.color( index );
        // Not using a loop here so we don't waste resources manipulating the
        // string, and we don't really need to import StringBuilder
        String hex = leadingZeroes( rgb[0], 16 ) + Integer.toHexString( rgb[0] )
                   + leadingZeroes( rgb[1], 16 ) + Integer.toHexString( rgb[1] ) 
                   + leadingZeroes( rgb[2], 16 ) + Integer.toHexString( rgb[2]);
        return hex;
    }
    // Add leading zero for num, if necessary
    private String leadingZeroes( int num, int base ) {
        if( num < base )
            return "0";
        return "";
    }
}
