# Slash Course API

The RESTful API which allows users to view all slash courses. Users can also update or delete slash course data from the database.

## Description

There are two resources in the Slash Course API: **/slash\_courses** and **/slash\_courses/{crn}**
* /slash_courses supports **GET** and **POST**.
    * In GET /slash_course, users are allowed to query all slash courses information or to filter the results by giving parameters (by course name, term or department).
    * In POST /slash_course, users are allowed to add a new course resource. 
* /slash_courses/{crn} supports **GET**, **PUT** and **DELETE**
    * In GET /slash_course/{crn}, users are allowed to query one specific slash course information by CRN.
    * In PUT /slash_course/{crn}, users are allowed to update a new course information by CRN.
    * In DELETE /slash_course/{crn}, users are allowed to delete a existed course object by CRN.

## Installation

You'll need to use [Gradle](http://gradle.org/) to build the API and obtain the `ojdbc6_g.jar` file from [Oracle](http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html) and put it into the `bin` directory. In addition, you'll also have to create a `configuration.yaml` file which includes your personal credentials. After finish the process, you can simply run the follow command to activate the API service:<br/>

`gradle run`

By default, the API uses the port `8080`, so in order to test the API locally, you can connect `localhost:8080\` from your browser.

## GET

Retrieve the slash course info from the database

### GET All

Respond to GET requests and show all courses information.

```
> GET /api/v0/slash_course

[
  {
    "crn": 58736,
    "courseNum": "CS544",
    "courseName": "CS 544. OPERATING SYSTEMS II",
    "slash": 1,
    "term": "Sp16",
    "instructor": {
      "instructorId": 1,
      "lastName": "McGrath",
      "firstName": "Kevin"
    },
    "day": "TR",
    "time": "0830-0950",
    "location": "GLFN AUD",
    "type": "Lecture"
  },
  {
    "crn": 32432,
    "courseNum": "ST511",
    "courseName": "ST 511. METHODS OF DATA ANALYSIS",
    "slash": 1,
    "term": "W16",
    "instructor": {
      "instructorId": 2,
      "lastName": "Emerson",
      "firstName": "Sarah"
    },
    "day": "MWF",
    "time": "0800-0850",
    "location": "MCC 201",
    "type": "Lecture"
  },
  {
    "crn": 53901,
    "courseNum": "CS569",
    "courseName": "CS 569. SELECTED TOPICS IN SOFTWARE ENGINEERING",
    "slash": 0,
    "term": "Sp16",
    "instructor": {
      "instructorId": 3,
      "lastName": "Groce",
      "firstName": "Alex"
    },
    "day": "MWF",
    "time": "1500-1550",
    "location": "Corv",
    "type": "Lecture"
  }
]
```

### GET by CRN

Respond to GET requests and show course information according to course CRN.

```
> GET /api/v0/slash_course/58736

{
  "crn": 58736,
  "courseNum": "CS544",
  "courseName": "CS 544. OPERATING SYSTEMS II",
  "slash": 1,
  "term": "Sp16",
  "instructor": {
    "instructorId": 1,
    "lastName": "McGrath",
    "firstName": "Kevin"
  },
  "day": "TR",
  "time": "0830-0950",
  "location": "GLFN AUD",
  "type": "Lecture"
}
```