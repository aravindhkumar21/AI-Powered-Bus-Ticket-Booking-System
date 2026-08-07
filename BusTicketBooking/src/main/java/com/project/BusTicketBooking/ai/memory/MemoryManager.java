package com.project.BusTicketBooking.ai.memory;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class MemoryManager {

    private final ConcurrentHashMap<String, ConversationMemory> memories =
            new ConcurrentHashMap<>();

    public ConversationMemory getMemory(String userId) {

        return memories.computeIfAbsent(
                userId,
                id -> new ConversationMemory()
        );
    }

    public void clearMemory(String userId) {

        memories.remove(userId);
    }
}