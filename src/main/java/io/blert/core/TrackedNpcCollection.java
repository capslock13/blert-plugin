/*
 * Copyright (c) 2024 Alexei Frolov
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

package io.blert.core;

import lombok.NonNull;
import net.runelite.api.NPC;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

public class TrackedNpcCollection implements Collection<TrackedNpc> {
    private final HashMap<Long, TrackedNpc> byRoomId = new HashMap<>();
    private final HashMap<Integer, TrackedNpc> byNpc = new HashMap<>();

    public TrackedNpcCollection() {
    }

    public Optional<TrackedNpc> getByRoomId(long roomId) {
        return Optional.ofNullable(byRoomId.get(roomId));
    }

    public Optional<TrackedNpc> getByNpc(NPC npc) {
        return Optional.ofNullable(byNpc.get(npc.hashCode()));
    }

    @Override
    public int size() {
        return byRoomId.size();
    }

    @Override
    public boolean isEmpty() {
        return byRoomId.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof TrackedNpc)) {
            return false;
        }

        TrackedNpc trackedNpc = (TrackedNpc) o;
        return byRoomId.containsKey(trackedNpc.getRoomId());
    }

    @NonNull
    @Override
    public Iterator<TrackedNpc> iterator() {
        return byRoomId.values().iterator();
    }

    @NonNull
    @Override
    public TrackedNpc @NonNull [] toArray() {
        return byRoomId.values().toArray(new TrackedNpc[0]);
    }

    @NonNull
    @Override
    public <T> T @NonNull [] toArray(@NonNull T @NonNull [] ts) {
        return byRoomId.values().toArray(ts);
    }

    @Override
    public boolean add(TrackedNpc trackedNpc) {
        byRoomId.put(trackedNpc.getRoomId(), trackedNpc);
        byNpc.put(trackedNpc.getNpc().hashCode(), trackedNpc);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof TrackedNpc)) {
            return false;
        }

        TrackedNpc trackedNpc = (TrackedNpc) o;
        byNpc.remove(trackedNpc.getNpc().hashCode());
        return byRoomId.remove(trackedNpc.getRoomId()) != null;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return collection.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends TrackedNpc> collection) {
        int sizeBefore = byRoomId.size();
        collection.forEach(this::add);
        return byRoomId.size() != sizeBefore;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        int sizeBefore = byRoomId.size();
        collection.forEach(this::remove);
        return byRoomId.size() != sizeBefore;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        int sizeBefore = byRoomId.size();
        byRoomId.values().retainAll(collection);
        byNpc.values().retainAll(collection);
        return byRoomId.size() != sizeBefore;
    }

    @Override
    public void clear() {
        byRoomId.clear();
        byNpc.clear();
    }
}
