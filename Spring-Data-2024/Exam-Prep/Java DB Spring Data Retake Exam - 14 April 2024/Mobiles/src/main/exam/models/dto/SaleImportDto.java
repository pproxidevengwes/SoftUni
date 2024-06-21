package softuni.exam.models.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import softuni.exam.models.entity.Seller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class SaleImportDto implements Serializable {
    @Expose
    @Nullable
    private boolean discounted;
    @Expose
    @Size(min = 7, max = 7)
    @NotNull
    private String number;
    @Expose
    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime saleDate;
    @Expose
    @Nullable
    private String seller;

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    @Nullable
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
