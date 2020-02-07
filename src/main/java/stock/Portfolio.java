package stock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Share> portfolio = new HashMap<>();

    public Share getShare(String name) {
        return portfolio.get(name);
    }

    public void putShare(String name, Share shareObject) {
        portfolio.put(name, shareObject);
    }

    public void sellShare(String name, int quantity, LocalDate date) {
        Share shareObject = getShare(name);
        if(shareObject == null) {
            shareObject = new Share(name);
            putShare(name, shareObject);
        }

        shareObject.sell(quantity, date);
    }

    public void buyShare(String name, int quantity, LocalDate date) {
        Share shareObject = getShare(name);
        if(shareObject == null) {
            shareObject = new Share(name);
            putShare(name, shareObject);
        }

        shareObject.buy(quantity, date);
    }

    public int shareBalance(String name) {
        Share shareObject = getShare(name);
        if(shareObject == null) {
            return 0;
        }
        return shareObject.balance();
    }

    public void setSharePrice(String name, BigDecimal amount) {
        Share shareObject = getShare(name);
        if(shareObject == null) {
            shareObject = new Share(name);
            putShare(name, shareObject);
        }

        shareObject.setCurrentPrice(amount);
    }

    public BigDecimal shareValue(String name) {
        return null;
    }
}
