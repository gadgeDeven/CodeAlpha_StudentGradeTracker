<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Edit Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">  <!-- Icons -->
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header bg-warning text-white">
                        <h2 class="mb-0"><i class="fas fa-user-edit me-2"></i>Edit Student Grades</h2>
                    </div>
                    <div class="card-body">
                        <c:if test="${error != null}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                ${error}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>
                        <c:if test="${success != null}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                ${success}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>
                        <form:form method="post" action="/students/update/${student.id}" modelAttribute="student">
                            <form:hidden path="id"/>
                            <div class="mb-3">
                                <label class="form-label"><i class="fas fa-user me-1"></i>Student Name:</label>
                                <form:input path="name" class="form-control" required="true" placeholder="Enter full name"/>
                                <form:errors path="name" cssClass="text-danger"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><i class="fas fa-graduation-cap me-1"></i>Grades (comma-separated):</label>
                                <input type="text" name="gradesString" class="form-control" required="true" value="${gradesString}" placeholder="85,90,78"/>
                                <div class="form-text">Grades between 0-100. Example: 85,92,78</div>
                            </div>
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <button type="submit" class="btn btn-warning me-md-2"><i class="fas fa-save me-1"></i>Update Student</button>
                                <a href="/students" class="btn btn-secondary"><i class="fas fa-times me-1"></i>Cancel</a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>