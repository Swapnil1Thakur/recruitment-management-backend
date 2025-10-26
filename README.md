# Recruitment Management System – Backend
**Tech Stack:** **Java, Spring Boot, Hibernate, JWT, REST APIs**

# Project Overview
This is a backend server for a Recruitment Management System.
It allows applicants to create profiles, upload resumes, and apply for jobs**, while admins can manage job openings and track applicants.  
Uploaded resumes are automatically parsed using a third-party API to extract skills, education, and experience.

# Features Implemented
1. User Management: Signup & login with **JWT based authentication**.
2. Resume Upload & Parsing: Applicants upload **PDF/DOCX resumes**. Integrated with **Resume Parser API** to extract and store relevant data.  
3. Job Management (Admin Only): Create job postings, view all jobs, and track applicants.  
4. Applicant Actions: View job openings and apply for jobs.  
5. Role-based Access Control: Only **Admins or Applicants** can access their respective endpoints.

# API Overview
1. Users can **sign up and log in** with JWT authentication.  2.
2. Applicants can **upload resumes** and **apply to jobs**.  
3. Admins can **create and manage job postings** and **view applicants**.
4. All interactions follow **role-based access control**.

# How Resume Parsing Works
1. Upload resumes via /uploadResume  
2. Resume is sent to **Resume Parser API** ([https://api.apilayer.com/resume_parser/upload](https://api.apilayer.com/resume_parser/upload)).  
3. API returns JSON with fields like education, skills, experience, email, phone.  
4. Extracted data is saved into the Profile table.  
5. Missing fields are stored as empty/null.

# Why This Project Stands Out
1. Automated Resume Parsing: Saves admin time by extracting & storing relevant info automatically.  
2. Secure & Scalable: Implements JWT authentication and role-based access.  
3. Real-world Use Case: Covers full backend workflow of a recruitment system.  
4. Clean Architecture: Uses Controllers, Services, and Repositories for maintainability.

# My Learning: 
1. I learned how to build REST APIs in Spring Boot and add authentication with JWT and role-based access.
2. I got to work with external APIs to parse resumes and extract data automatically.
3. I figured out how to **handle file uploads** and save data properly using Hibernate.
4. This project helped me understand how a **real-world backend system is structured** and how Controllers, Services, and Repositories work together.

# Testing
1. APIs can be tested using Postman with JWT tokens. 
2. Resume upload tested with PDF/DOCX files to ensure parsed data is saved correctly.

# Conclusion
This project demonstrates my ability to design and implement a robust backend system with:

1. Authentication & role-based access
2. File handling & parsing
3. External API integration
4. Clean, maintainable architecture
   
Hence, this project also helped me gain real-world backend development experience and strengthened my skills in building secure, maintainable, and practical systems.
