import java.time.LocalDate;
import java. text.Format;

public class Sale {
    private Product productSold;
    private LocalDate date;
    private int uni;
    private Double valueSold;

    public Double getValueSold() {
        return valueSold;
    }


    public void setValueSold(Double valueSold) {
        this.valueSold = valueSold;
    }


    public Sale(Product productSold, LocalDate date, int uni, Double valueSold) {
        this.productSold = productSold;
        this.date = date;
        this.uni = uni;
        this.valueSold = valueSold;
    }


    @Override
    public String toString() {
        return String.format("Data: %s\nProduto: %s\nQuantidade: %s\nValor unitário: %s\nValor total: %s\n\n", date, productSold.getName(), uni, productSold.getPrice(), valueSold);
    }

    public Product getProductSold() {
        return productSold;
    }
    public void setProductSold(Product productSold) {
        this.productSold = productSold;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getUni() {
        return uni;
    }
    public void setUni(int uni) {
        this.uni = uni;
    }
    

}
