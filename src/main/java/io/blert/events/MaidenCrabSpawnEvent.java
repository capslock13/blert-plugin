/*
 * Copyright (c) 2023-2024 Alexei Frolov
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

package io.blert.events;

import io.blert.raid.rooms.Room;
import io.blert.raid.rooms.maiden.CrabSpawn;
import io.blert.raid.rooms.maiden.MaidenCrab;
import lombok.Getter;

@Getter
public class MaidenCrabSpawnEvent extends Event {
    private final CrabSpawn spawn;
    private final MaidenCrab crab;

    public MaidenCrabSpawnEvent(int tick, CrabSpawn spawn, MaidenCrab crab) {
        super(EventType.MAIDEN_CRAB_SPAWN, Room.MAIDEN, tick, crab.getSpawnPoint());
        this.spawn = spawn;
        this.crab = crab;
    }

    @Override
    protected String eventDataString() {
        StringBuilder sb = new StringBuilder("crab_spawn=(");
        sb.append("spawn=").append(spawn);
        sb.append(", crab=");
        if (crab.isScuffed()) {
            sb.append("scuffed ");
        }
        sb.append(crab.getPosition());
        sb.append(')');
        return sb.toString();
    }
}