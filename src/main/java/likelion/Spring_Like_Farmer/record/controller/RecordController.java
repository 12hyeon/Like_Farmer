package likelion.Spring_Like_Farmer.record.controller;

import likelion.Spring_Like_Farmer.security.CurrentUser;
import likelion.Spring_Like_Farmer.record.dto.RecordDto;
import likelion.Spring_Like_Farmer.record.service.RecordService;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/record")
@Controller
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @PostMapping
    public ResponseEntity<Object> saveRecord(@CurrentUser UserPrincipal recordPrincipal,
                                             @RequestBody RecordDto.SaveRecord saveRecord) {
        return new ResponseEntity<>(recordService.saveRecord(recordPrincipal, saveRecord), HttpStatus.OK);
    }

    /*@GetMapping("/record/{recordId}") // 리스트
    public ResponseEntity<Object> findRecords(@CurrentUser UserPrincipal recordPrincipal,
                                           @PathVariable Long recordId) {
        return new ResponseEntity<>(recordService.findRecords(recordPrincipal, recordId), HttpStatus.OK);
    }*/

    @PatchMapping("/{recordId}")
    public ResponseEntity<Object> updateRecords(@CurrentUser UserPrincipal recordPrincipal,
                                                @PathVariable Long recordId,
                                                @RequestBody RecordDto.SaveRecord saveRecord) {
        return new ResponseEntity<>(recordService.updateRecord(recordPrincipal, recordId, saveRecord), HttpStatus.OK);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Object> deleteRecords(@CurrentUser UserPrincipal recordPrincipal,
                                                @PathVariable Long recordId) {
        return new ResponseEntity<>(recordService.deleteRecord(recordPrincipal, recordId), HttpStatus.OK);
    }
}
