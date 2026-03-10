package com.optica.manager.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.optica.manager.domain.services.FrameService;
import com.optica.manager.domain.services.StockMovementService;
import com.optica.manager.dto.FrameRequest;
import com.optica.manager.dto.FrameResponse;
import com.optica.manager.dto.StockRequest;

@RestController
@RequestMapping("frames")
public class FrameController {

    @Autowired
    private FrameService frameService;

    @Autowired
    private StockMovementService stockMovementService;

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<FrameResponse>> getFramesPage(
            @RequestParam(name = "name_like", defaultValue = "") String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok(frameService.findByNameContainingIgnoreCase(name, page, size));
    }

    @GetMapping
    public ResponseEntity<List<FrameResponse>> getFrames() {
        var frames = frameService.getFrames();
        return ResponseEntity.ok(frames);
    }

    @GetMapping("{id}")
    public ResponseEntity<FrameResponse> getFrame(@PathVariable long id) {
        var frame = frameService.getById(id);
        return ResponseEntity.ok(frame);
    }

    @PostMapping
    public ResponseEntity<FrameResponse> saveFrame(@Validated @RequestBody FrameRequest frameRequest) {
        var frameResponse = frameService.save(frameRequest);

        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(frameResponse.id()).toUri();

        return ResponseEntity.created(location).body(frameResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateFrame(@PathVariable long id,
            @Validated @RequestBody FrameRequest frameRequest) {
        frameService.update(id, frameRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFrame(@PathVariable long id) {
        frameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/stock/entry")
    public ResponseEntity<Void> increaseStock(@PathVariable Long id, @RequestBody StockRequest stockRequest) {
        stockMovementService.increaseStock(id, stockRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/stock/exit")
    public ResponseEntity<Void> decreaseStock(@PathVariable Long id, @RequestBody StockRequest stockRequest) {
        stockMovementService.decreaseStock(id, stockRequest);
        return ResponseEntity.noContent().build();
    }
    
}
