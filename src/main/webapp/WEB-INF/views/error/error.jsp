<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web</title>
    <script>
        /**
         * [[ code ]]
         * 0    : SUCCESS
         * -1   : 시스템에 문제가 발생했습니다. 다시 시도해주세요.
         * -2   : 존재하지 않는 데이터입니다.
         * -999 : 예상치 못한 오류가 발생했습니다.
         * */
        let code = "${code}";

        if (code == -1)
            message = "시스템에 문제가 발생했습니다. 다시 시도해주세요. ";
        else if (code == -2)
            message = "존재하지 않는 데이터입니다.";
        else if (code == -999)
            message = "예상치 못한 오류가 발생했습니다.";

        alert(message);
        history.back();
    </script>
</head>
<body>
</body>
</html>