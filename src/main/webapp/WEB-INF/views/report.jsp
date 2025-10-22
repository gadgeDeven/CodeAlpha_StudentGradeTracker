<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Grades Report</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">  <!-- Icons -->
</head>
<body>
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-info text-white">
                <h2 class="mb-0"><i class="fas fa-chart-line me-2"></i>Grades Summary Report</h2>
            </div>
            <div class="card-body">
                <a href="/students" class="btn btn-secondary mb-3"><i class="fas fa-arrow-left me-1"></i>Back to List</a>
                
                <!-- Quick Summary Stats -->
                <c:if test="${not empty students}">
                    <div class="row mb-4">
                        <div class="col-md-3">
                            <div class="card bg-primary text-white text-center">
                                <div class="card-body">
                                    <h5><i class="fas fa-users"></i> Total Students</h5>
                                    <h3>${students.size()}</h3>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card bg-success text-white text-center">
                                <div class="card-body">
                                    <h5><i class="fas fa-chart-bar"></i> Overall Avg</h5>
                                    <h3>
                                        <c:set var="totalAvg" value="0.0" />
                                        <c:forEach var="s" items="${students}">
                                            <c:set var="totalAvg" value="${totalAvg + s.average}" />
                                        </c:forEach>
                                        ${totalAvg / students.size()}
                                    </h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>Name</th>
                                <th>Grades</th>
                                <th>Average</th>
                                <th>Highest</th>
                                <th>Lowest</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.name}</td>
                                    <td>${student.grades}</td>
                                    <td><span class="badge bg-success">${student.average}</span></td>
                                    <td><span class="badge bg-warning">${student.highest}</span></td>
                                    <td><span class="badge bg-secondary">${student.lowest}</span></td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty students}">
                                <tr>
                                    <td colspan="5" class="text-center text-muted">No data for report. Add students first!</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>