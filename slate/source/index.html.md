---
title: Slash Course API Reference

language_tabs:
  - shell

toc_footers:
  - <a href='https://developer.oregonstate.edu/'>Register for an API token</a>
  - <a href='https://github.com/osu-mist/slash-course-api/tree/develop'>GitHub repository</a>

includes:
  - errors

search: true
---

# Introduction

This is a RESTful API which allows users to view all OSU slash courses. Users can also update or delete slash course data from the database. You can check the original GitHub repo [here](https://github.com/osu-mist/slash-course-api/tree/develop).

# Description

There are two resources in the Slash Course API: **/slash_courses and /slash_courses/{crn}**

* /slash_courses supports **GET** and **POST**.
  * In GET /slash_course, users are allowed to query all slash courses information or to filter the results by giving parameters (by course name, term or department).
  * In POST /slash_course, users are allowed to add a new course resource.
* /slash_courses/{crn} supports **GET**, **PUT** and **DELETE**
  * In GET /slash_course/{crn}, users are allowed to query one specific slash course information by CRN.
  * In PUT /slash_course/{crn}, users are allowed to update a new course information by CRN.
  * In DELETE /slash_course/{crn}, users are allowed to delete a existed course object by CRN.

# Installation

You'll need to use Gradle to build the API and obtain the `ojdbc6_g.jar` file from Oracle and put it into the `bin` directory. In addition, you'll also have to create a `configuration.yaml` file which includes your personal credentials. After finish the process, you can simply run the follow command to activate the API service: `gradle run`

```shell
gradle run
```

By default, the API uses the port `8080`, so in order to test the API locally, you can access `https://localhost:8080` from your browser.

# Slash Courses

## GET all slash courses or by giving filters

```shell
curl "https://localhost:8080/api/v0/slash_course"
```

> The above command returns JSON structured like this:

```json
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
    "crn": 55504,
    "courseNum": "CS572",
    "courseName": "CS 572. COMPUTER ARCHITECTURE",
    "slash": 1,
    "term": "Sp16",
    "instructor": {
      "instructorId": 4,
      "lastName": "Chen",
      "firstName": "Lizhong"
    },
    "day": "MWF",
    "time": "1100-1150",
    "location": "GLFN AUD",
    "type": "Lecture"
  },
  {
    "crn": 16695,
    "courseNum": "CS572",
    "courseName": "CS 572. COMPUTER ARCHITECTURE",
    "slash": 1,
    "term": "F16",
    "instructor": {
      "instructorId": 1,
      "lastName": "McGrath",
      "firstName": "Kevin"
    },
    "day": "TR",
    "time": "1400-1520",
    "location": "KIDD 364",
    "type": "Lecture"
  },
  {
    "crn": 13067,
    "courseNum": "CS550",
    "courseName": "CS 550. INTRODUCTION TO COMPUTER GRAPHICS",
    "slash": 1,
    "term": "F16",
    "instructor": {
      "instructorId": 6,
      "lastName": "Bailey",
      "firstName": "Mike"
    },
    "day": "MWF",
    "time": "1300-1350",
    "location": "WNGR 116",
    "type": "Lecture"
  }
]
```

```shell
curl "https://localhost:8080/api/v0/slash_courses?department=CS&slash=0"
```

> The above command returns JSON structured like this:

```json
[
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
    "location": "BAT 150",
    "type": "Lecture"
  },
  {
    "crn": 10459,
    "courseNum": "CS515",
    "courseName": "CS 515. ALGORITHMS AND DATA STRUCTURES",
    "slash": 0,
    "term": "F16",
    "instructor": {
      "instructorId": 5,
      "lastName": "Amir",
      "firstName": "Nayyeri"
    },
    "day": "MW",
    "time": "1000-1150",
    "location": "KEAR 212",
    "type": "Lecture"
  }
]
```

This endpoint retrieves all slash courses.

### HTTP Request

`GET https://localhost:8080/api/v0/slash_course`

### Query Parameters

Parameter | Default | Description
--------- | ------- | -----------
department | all | If set to specific department (e.g. CS), the result will only include that department courses.
slash | 1 | If set to 0, the result will only include non-slash courses.

## Get a Slash Course

```shell
curl "https://localhost:8080/api/v0/slash_course/58736"
```

> The above command returns JSON structured like this:

```json
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
}
]
```

This endpoint retrieves a specific slash course.

### HTTP Request

`GET https://localhost:8080/api/v0/slash_course/{crn}`

### URL Parameters

Parameter | Description
--------- | -----------
CRN | The CRN of the slash course to retrieve
