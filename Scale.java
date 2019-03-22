package rainbowscaledriver;

import java.math.BigDecimal;

public class Scale {
    public BigDecimal coefficient;  // Currently using BigDecimal as the underlying data structure,
                                    // however a more generic implementation would be preferred.
                                    // Another option is to implement continuous and
                                    // discrete subclasses, using BigDecimal and BigInteger, respectively,
                                    // due to similar interfaces
    public Scale( BigDecimal coefficient ) {
        this.coefficient = coefficient;
    }
    public BigDecimal getValue( UnitInterval factor ) {
        return this.coefficient.multiply( factor.getValue() );
    }
}
