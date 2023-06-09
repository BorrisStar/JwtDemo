package com.example.jwtdemo.rest.advice.problem;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;

import java.net.URI;
import java.util.Optional;

@JsonPropertyOrder(
        alphabetic = true
)
@RequiredArgsConstructor
public class CommonProblem extends ThrowableProblem {
    private final String detail;
    private final URI instance;
    private final Status status;
    private final String title;
    private final URI type;

    public static CommonProblemBuilder builder() {
        return new CommonProblemBuilder();
    }

    public String getDetail() {
        return this.detail;
    }

    public URI getInstance() {
        return this.instance;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getTitle() {
        return this.title;
    }

    public URI getType() {
        return this.type;
    }


    public static class CommonProblemBuilder {
        private String detail;
        private URI instance;
        private Status status;
        private String title;
        private URI type;

        private CommonProblemBuilder() {
        }

        public static CommonProblemBuilder prepareVrpProblemWithDetail(Exception exception, NativeWebRequest request, Status status, URI type) {
            return prepareVrpProblemWithoutDetail(request, status, type).detail(exception.getMessage());
        }

        public static CommonProblemBuilder prepareVrpProblemWithoutDetail(NativeWebRequest request, StatusType status, URI type) {
            return CommonProblem.builder().type(type).title(status.getReasonPhrase()).status(prepareStatus(status)).instance(prepareInstance(request));
        }

        private static Status prepareStatus(StatusType status) {
            return Optional.ofNullable(status).map(StatusType::getStatusCode).map(Status::valueOf).orElse(Status.INTERNAL_SERVER_ERROR);
        }

        private static URI prepareInstance(NativeWebRequest request) {
            return Optional.ofNullable(request).map((r) -> r.getNativeRequest(HttpServletRequest.class))
                    .map(HttpServletRequest::getRequestURI).map(URI::create).orElse(null);
        }

        public CommonProblemBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public CommonProblemBuilder instance(URI instance) {
            this.instance = instance;
            return this;
        }

        public CommonProblemBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public CommonProblemBuilder title(String title) {
            this.title = title;
            return this;
        }

        public CommonProblemBuilder type(URI type) {
            this.type = type;
            return this;
        }

        public CommonProblem build() {
            return new CommonProblem(this.detail, this.instance, this.status, this.title, this.type);
        }

        public String toString() {
            return "Problem.ProblemBuilder(detail=" + this.detail + ", instance=" + this.instance + ", status=" + this.status + ", title=" + this.title + ", type=" + this.type + ", violations=" + ")";
        }
    }
}
