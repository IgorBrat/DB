package com.lviv.iot.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EventEquipmentPK implements Serializable {
    @Column(name = "event_id")
    @Id
    private Integer eventId;
    @Column(name = "equipment_id")
    @Id
    private Integer equipmentId;

    public Integer getEvent() {
        return eventId;
    }

    public void setEvent(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getEquipment() {
        return equipmentId;
    }

    public void setEquipment(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEquipmentPK that = (EventEquipmentPK) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(equipmentId, that.equipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, equipmentId);
    }
}
