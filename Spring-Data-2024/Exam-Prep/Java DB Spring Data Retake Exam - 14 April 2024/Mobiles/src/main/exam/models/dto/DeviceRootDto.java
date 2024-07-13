package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceRootDto implements Serializable {
    @XmlElement(name = "device")
    private List<DeviceImportDto> DeviceImportDtotoList;

    public List<DeviceImportDto> getDeviceImportDtotoList() {
        return DeviceImportDtotoList;
    }

    public void setDeviceImportDtotoList(List<DeviceImportDto> deviceImportDtotoList) {
        this.DeviceImportDtotoList = deviceImportDtotoList;
    }
}
