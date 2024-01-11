/*
 * Copyright (c) 2023 Alexei Frolov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.blert.raid;

import lombok.Getter;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.kit.KitType;

import javax.annotation.Nullable;

@Getter
public enum EquipmentSlot {
    // KitType represents an item slot that is visibly rendered on players, so they do not map 1:1 to equipment slots.
    HEAD(EquipmentInventorySlot.HEAD, KitType.HEAD),
    CAPE(EquipmentInventorySlot.CAPE, KitType.CAPE),
    AMULET(EquipmentInventorySlot.AMULET, KitType.AMULET),
    AMMO(EquipmentInventorySlot.AMMO, null),
    WEAPON(EquipmentInventorySlot.WEAPON, KitType.WEAPON),
    TORSO(EquipmentInventorySlot.BODY, KitType.TORSO),
    SHIELD(EquipmentInventorySlot.SHIELD, KitType.SHIELD),
    LEGS(EquipmentInventorySlot.LEGS, KitType.LEGS),
    GLOVES(EquipmentInventorySlot.GLOVES, KitType.HANDS),
    BOOTS(EquipmentInventorySlot.BOOTS, KitType.BOOTS),
    RING(EquipmentInventorySlot.RING, null);

    private final int inventorySlotIndex;
    private final @Nullable KitType kitType;

    EquipmentSlot(EquipmentInventorySlot runeliteSlot, @Nullable KitType kitType) {
        this.inventorySlotIndex = runeliteSlot.getSlotIdx();
        this.kitType = kitType;
    }
}
