FROM postgres:17

# Copy SQL script into the container
COPY init.sql /docker-entrypoint-initdb.d/

# Environment variables
ENV POSTGRES_DB=tutoring
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres