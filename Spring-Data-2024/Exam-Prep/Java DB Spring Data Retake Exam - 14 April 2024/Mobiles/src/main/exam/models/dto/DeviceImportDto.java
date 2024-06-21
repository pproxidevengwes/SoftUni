package softuni.exam.models.dto;

import org.springframework.lang.Nullable;
import softuni.exam.models.entity.DeviceType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceImportDto implements Serializable {
    @XmlElement
    @Size(min = 2, max = 20)
    @NotNull
    private String brand;
    @XmlElement(name = "device_type")
    private DeviceType deviceType;
    @XmlElement
    @Size(min = 1, max = 20)
    @NotNull
    private String model;
    @XmlElement
    @Nullable
    private double price;
    @XmlElement
    @Positive
    @Nullable
    private int storage;
    @XmlElement(name = "sale_id")
    @Nullable
    private long sale;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }
}
