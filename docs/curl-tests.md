# Curl tests

Assume the app is running on `http://localhost:8080`.
Replace placeholders like `USER_ID` with real UUIDs from prior responses.

## Users

Create user
```bash
curl -i -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Juan","apellidoPaterno":"Perez","apellidoMaterno":"Lopez","username":"usuario1","password":"secreto123","carrera":"Sistemas","boleta":"20230001","email":"juan.perez@example.com"}'
```

Login
```bash
curl -i -X POST http://localhost:8080/api/users/login \
  -H "Content-Type: application/json" \
  -d '{"username":"usuario1","password":"secreto123"}'
```

Update user
```bash
curl -i -X PUT http://localhost:8080/api/users/USER_ID \
  -H "Content-Type: application/json" \
  -d '{"name":"Juan","apellidoPaterno":"Perez","apellidoMaterno":"Lopez","carrera":"Sistemas","email":"juan.perez@example.com"}'
```

Get users
```bash
curl -i http://localhost:8080/api/users
curl -i http://localhost:8080/api/users/USER_ID
```

## Lockers

Create locker (tipo: SMALL|MEDIUM|LARGE)
```bash
curl -i -X POST http://localhost:8080/api/lockers \
  -H "Content-Type: application/json" \
  -d '{"numero":"L-101","edificio":"A","piso":"1","tipo":"MEDIUM"}'
```

Update locker (full)
```bash
curl -i -X PUT http://localhost:8080/api/lockers/LOCKER_ID \
  -H "Content-Type: application/json" \
  -d '{"numero":"L-101","edificio":"A","piso":"1","tipo":"MEDIUM","status":"DISPONIBLE"}'
```

Edit locker (partial)
```bash
curl -i -X PATCH http://localhost:8080/api/lockers/LOCKER_ID/edit \
  -H "Content-Type: application/json" \
  -d '{"numero":"L-102","edificio":"A","piso":"1","tipo":"LARGE"}'
```

Get lockers
```bash
curl -i http://localhost:8080/api/lockers
curl -i http://localhost:8080/api/lockers/LOCKER_ID
```

## Assignments

Assign locker
```bash
curl -i -X POST http://localhost:8080/api/assignments \
  -H "Content-Type: application/json" \
  -d '{"userId":"USER_ID","lockerId":"LOCKER_ID"}'
```

Unassign
```bash
curl -i -X PUT http://localhost:8080/api/assignments/ASSIGNMENT_ID/unassign
```

Get assignments
```bash
curl -i http://localhost:8080/api/assignments
curl -i http://localhost:8080/api/assignments/ASSIGNMENT_ID
```

## Locker Requests

Create request
```bash
curl -i -X POST http://localhost:8080/api/locker-requests/user/USER_ID \
  -H "Content-Type: application/json" \
  -d '{"lockerId":"LOCKER_ID"}'
```

Approve request
```bash
curl -i -X PUT http://localhost:8080/api/locker-requests/REQUEST_ID \
  -H "Content-Type: application/json" \
  -d '{"status":"APROBADO"}'
```

Reject request
```bash
curl -i -X PUT http://localhost:8080/api/locker-requests/REQUEST_ID \
  -H "Content-Type: application/json" \
  -d '{"status":"RECHAZADO","rejectionReason":"Sin disponibilidad"}'
```

Get locker requests
```bash
curl -i http://localhost:8080/api/locker-requests
curl -i http://localhost:8080/api/locker-requests/REQUEST_ID
```

## Mails

Create mail
```bash
curl -i -X POST http://localhost:8080/api/mails \
  -H "Content-Type: application/json" \
  -d '{"from":"noreply@example.com","to":"test@example.com","subject":"Prueba","body":"Hola"}'
```

Get mails
```bash
curl -i http://localhost:8080/api/mails
curl -i http://localhost:8080/api/mails/MAIL_ID
```

## Error cases

Invalid UUID (400)
```bash
curl -i http://localhost:8080/api/lockers/INVALID_UUID
```

Not found (404)
```bash
curl -i http://localhost:8080/api/users/00000000-0000-0000-0000-000000000000
```

Duplicate unique value (409)
```bash
curl -i -X PUT http://localhost:8080/api/lockers/LOCKER_ID \
  -H "Content-Type: application/json" \
  -d '{"numero":"L-101","edificio":"A","piso":"1","tipo":"MEDIUM","status":"DISPONIBLE"}'
```
