package com.ninjaone.it.service;

/**
 * This enum defines IT service type ids.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public enum ITServiceType {
	ANTIVIRUS(1),
    BACKUP(2),
    SCREEN_SHARE(3),
    DEVICE_OWNERSHIP(4);

    private final int typeId;

    ITServiceType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

}
