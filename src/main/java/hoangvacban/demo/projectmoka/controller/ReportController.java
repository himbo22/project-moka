package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.entity.Report;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportController {

    ReportService reportService;

    @PostMapping("report/{userId}/{postId}")
    public ResponseObject report(@PathVariable Long userId, @PathVariable Long postId, @RequestBody String reason) {
        Report report = reportService.addReport(userId, postId, reason);
        return new ResponseObject("ok", "ok", report);
    }

}
