package com.tushar.cowin.notifier.api;

import com.tushar.cowin.notifier.model.dto.response.add.AddEntryResponse;
import com.tushar.cowin.notifier.model.dto.response.add.NotificationEntryRequestDto;
import com.tushar.cowin.notifier.model.dto.response.delete.DeleteEntryDto;
import com.tushar.cowin.notifier.model.dto.response.find.FindNotificationEntryDto;
import com.tushar.cowin.notifier.model.dto.response.notification_entry.NotificationEntryDto;
import com.tushar.cowin.notifier.model.entity.AgeGroup;
import com.tushar.cowin.notifier.model.entity.NotificationEntry;
import com.tushar.cowin.notifier.model.entity.Vaccine;
import com.tushar.cowin.notifier.service.notificationentry.NotificationEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.*;


@RestController
@RequiredArgsConstructor
public class NotificationEntryApi {

    private final NotificationEntryService notificationEntryService;

    @ResponseBody
    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AddEntryResponse> addEntry(@RequestBody NotificationEntryRequestDto notificationEntryRequestDto) {
        boolean status = notificationEntryService.addEntry(notificationEntryRequestDto);
        if(status) {
            return ResponseEntity.ok(getSuccessMsgForAdd(notificationEntryRequestDto));
        }
        return ResponseEntity.unprocessableEntity().body(getFailureMsgForAdd(notificationEntryRequestDto));
    }

    @GetMapping(value = "/delete",consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteEntryDto> deleteEntry(@RequestParam Integer id) {
        boolean status = notificationEntryService.deleteEntry(id);
        if(status) {
            return ResponseEntity.ok(new DeleteEntryDto("Successfully Deleted the Entry!"));
        }
        return ResponseEntity.unprocessableEntity().body(new DeleteEntryDto("Could Not Delete the Entry!"));
    }

    @GetMapping(value = "find", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FindNotificationEntryDto> findEntriesByPinCodePrefixAndVaccineAndAgeGroup(@RequestParam("pincode_prefix") String pinCodePrefix, @RequestParam("vaccine") Vaccine vaccine, @RequestParam("age_group") Integer ageGroup) {
        List<NotificationEntry> notificationEntries = notificationEntryService.findEntryByPinCodePrefixAndVaccineAndAgeGroup(pinCodePrefix, vaccine, AgeGroup.of(ageGroup));
        List<NotificationEntryDto> entryDtos = notificationEntries.stream().map(NotificationEntryDto::of).collect(Collectors.toList());
        return ResponseEntity.ok(new FindNotificationEntryDto(entryDtos));
    }


    private AddEntryResponse getSuccessMsgForAdd(NotificationEntryRequestDto notificationEntryRequestDto) {
        AddEntryResponse addEntryResponse = new AddEntryResponse("Successfully Added the Entry!", notificationEntryRequestDto);
        return addEntryResponse;
    }

    private AddEntryResponse getFailureMsgForAdd(NotificationEntryRequestDto notificationEntryRequestDto) {
        AddEntryResponse addEntryResponse = new AddEntryResponse("Could Not Added the Entry! Try Again Later.", notificationEntryRequestDto);
        return addEntryResponse;
    }

}
