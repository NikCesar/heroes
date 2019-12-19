package ch.bfh.swos.promoter.client;

import ch.bfh.swos.promoter.model.Party;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// Client will be implemented at runtime

@FeignClient(value = "arena-service", fallback = FallbackArenaClient.class)
public interface ArenaClient {

    @PostMapping(value = "/battle")
    String battle(@RequestBody List<Party> duelingParties);
}
