<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>  <!-- Updated to Jakarta -->
<html>
<head>
    <title>Students List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">  <!-- Icons -->
</head>
<body>
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0"><i class="fas fa-users me-2"></i>Students Grades Tracker</h2>
            </div>
            <div class="card-body">
                <div class="mb-4">
                    <a href="/students/add" class="btn btn-success me-2"><i class="fas fa-plus me-1"></i>Add New Student</a>
                    <a href="/students/report" class="btn btn-info"><i class="fas fa-chart-bar me-1"></i>View Report</a>
                </div>
                <c:if test="${success != null}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        ${success}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>
                <c:if test="${error != null}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${error}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Grades</th>
                                <th>Average</th>
                                <th>Highest</th>
                                <th>Lowest</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.id}</td>
                                    <td>${student.name}</td>
                                    <td>${student.grades}</td>
                                    <td><span class="badge bg-success">${student.average}</span></td>
                                    <td><span class="badge bg-warning">${student.highest}</span></td>
                                    <td><span class="badge bg-secondary">${student.lowest}</span></td>
                                    <td>
                                        <a href="/students/edit/${student.id}" class="btn btn-sm btn-warning me-1" title="Edit">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                        <a href="/students/delete/${student.id}" class="btn btn-sm btn-danger" onclick="return confirm('Delete ${student.name}?')" title="Delete">
                                            <i class="fas fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty students}">
                                <tr>
                                    <td colspan="7" class="text-center text-muted">No students yet. Add one above!</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Auto-dismiss alerts after 5s
        setTimeout(() => {
            document.querySelectorAll('.alert').forEach(alert => alert.style.transition = 'opacity 0.5s'; alert.style.opacity = 0);
        }, 5000);
    </script>
</body>
</html>