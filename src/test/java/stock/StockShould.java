package stock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class StockShould {

    private static final String OLD_SCHOOL_WATERFALL_SOFTWARE_LIMITED = "Old School Watrefall Softwrae LTD";
    private static final String CRAFTED_MASTERS = "Crafter Masters Limited";
    private static final String XP_PRACTITIONIOERS = "XP Practitioners Incorporated";

    static Portfolio portfolio;

    @BeforeAll
    public static void init() {
        portfolio = new Portfolio();
    }

    @Test
    public void Print_Portfolio_Criteria() {
        // Given
        I_Bought_Shares(OLD_SCHOOL_WATERFALL_SOFTWARE_LIMITED, 1000, LocalDate.of(1990, 2, 14));
        I_Bought_Shares(CRAFTED_MASTERS, 400, LocalDate.of(2016, 6, 9));
        I_Bought_Shares(XP_PRACTITIONIOERS, 700, LocalDate.of(2018, 12, 10));
        I_Sold_Shares(OLD_SCHOOL_WATERFALL_SOFTWARE_LIMITED, 500, LocalDate.of(2018, 12, 11));
        Current_Share_Value_Of(OLD_SCHOOL_WATERFALL_SOFTWARE_LIMITED, new BigDecimal("5.75"));
        Current_Share_Value_Of(CRAFTED_MASTERS, new BigDecimal("17.25"));
        Current_Share_Value_Of(XP_PRACTITIONIOERS, new BigDecimal("25.55"));

        // When
        I_Print_The_Portfolio();

        // Then
        String expectedOutcome = "company | shares | current price | current value | last operation\n" +
                "Old School Waterfall Software LTD | 500 | $5.75 | $2,875.00 | sold 500 on 11/12/2018\n" +
                "Crafter Masters Limited | 400 | $17.25 | $6,900.00 | bought 400 on 09/06/2016\n" +
                "XP Practitioners Incorporated | 700 | $25.55 | $17,885.00 | bought 700 on 10/12/2018";
        The_Outcome_Should_Be(expectedOutcome);
    }

    private void The_Outcome_Should_Be(String expectedOutcome) {
        assertThat(false);
    }

    private void I_Print_The_Portfolio() {

    }

    private void Current_Share_Value_Of(String name, BigDecimal amount) {
        //Share shareObject = portfolio.getShare(name);
        //assertThat(shareObject).isNotNull();
        // We know it's on the hash map
        //shareObject.setCurrentPrice(amount);
        portfolio.setSharePrice(name, amount);

        assertThat(portfolio.shareValue(name)).isEqualTo(BigDecimal.valueOf(portfolio.shareBalance(name)).multiply(amount));
    }

    private void I_Sold_Shares(String name, int quantity, LocalDate date) {
        // Given
        int currentBalance = portfolio.shareBalance(name);
        portfolio.sellShare(name, quantity, date);

        // Then
        assertThat(portfolio.shareBalance(name)).isEqualTo(currentBalance - quantity);
    }

    private void I_Bought_Shares(String name, int quantity, LocalDate date) {
        // Given
        int currentBalance = portfolio.shareBalance(name);
        portfolio.buyShare(name, quantity, date);
        // Then
        assertThat(portfolio.shareBalance(name)).isEqualTo(quantity + currentBalance);
    }
}
