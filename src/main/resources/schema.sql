--
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "contactinfodb";
--
-- Name: contactinfodb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "contactinfodb" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE "contactinfodb" OWNER TO postgres;

\connect -reuse-previous=on "dbname='contactinfodb'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE "contactinfodb"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "contactinfodb" IS 'Contact Info DB';


--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: addrtype-enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."addrtype-enum" AS ENUM (
    'home',
    'work'
);


ALTER TYPE public."addrtype-enum" OWNER TO postgres;

--
-- Name: state-enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."state-enum" AS ENUM (
    'AL',
    'AK',
    'AZ',
    'AR',
    'CA',
    'CO',
    'CT',
    'DE',
    'FL',
    'GA',
    'HI',
    'ID',
    'IL',
    'IN',
    'IA',
    'KS',
    'KY',
    'LA',
    'ME',
    'MD',
    'MA',
    'MI',
    'MN',
    'MS',
    'MO',
    'MT',
    'NE',
    'NV',
    'NH',
    'NJ',
    'NM',
    'NY',
    'NC',
    'ND',
    'OH',
    'OK',
    'OR',
    'PA',
    'RI',
    'SC',
    'SD',
    'TN',
    'TX',
    'UT',
    'VT',
    'VA',
    'WA',
    'WV',
    'WI',
    'WY'
);


ALTER TYPE public."state-enum" OWNER TO postgres;

--
-- Name: TYPE "state-enum"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TYPE public."state-enum" IS 'US States';


--
-- Name: addr-composite; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."addr-composite" AS (
	type public."addrtype-enum",
	number integer,
	street character varying(256),
	unit character varying(128),
	city character varying(128),
	state public."state-enum",
	zipcode character varying(16)
);


ALTER TYPE public."addr-composite" OWNER TO postgres;

--
-- Name: comm-type-enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."comm-type-enum" AS ENUM (
    'email',
    'cell'
);


ALTER TYPE public."comm-type-enum" OWNER TO postgres;

--
-- Name: TYPE "comm-type-enum"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TYPE public."comm-type-enum" IS 'Communication Type';


--
-- Name: gender-enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."gender-enum" AS ENUM (
    'M',
    'F',
    'O'
);


ALTER TYPE public."gender-enum" OWNER TO postgres;

--
-- Name: TYPE "gender-enum"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TYPE public."gender-enum" IS 'Gender';


--
-- Name: title-enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."title-enum" AS ENUM (
    'Manager',
    'Supervisor',
    'Senior Engineer',
    'Junior Engineer'
);


ALTER TYPE public."title-enum" OWNER TO postgres;

--
-- Name: TYPE "title-enum"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TYPE public."title-enum" IS 'Company Titles';


SET default_tablespace = '';

SET default_table_access_method = heap;

grant usage on schema public to public;
grant create on schema public to public;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;

--
-- Name: addressinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.addressinfo (
    addressid uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    address public."addr-composite" NOT NULL,
    contactid uuid NOT NULL
);


ALTER TABLE public.addressinfo OWNER TO postgres;

--
-- Name: basicinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.basicinfo (
    contactid uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    firstname character varying(64) NOT NULL,
    lastname character varying(64),
    birthdate date NOT NULL,
    gender public."gender-enum" NOT NULL,
    title public."title-enum" NOT NULL
);


ALTER TABLE public.basicinfo OWNER TO postgres;

--
-- Name: commpref; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.commpref (
    commid uuid NOT NULL,
    contactid uuid NOT NULL
);


ALTER TABLE public.commpref OWNER TO postgres;

--
-- Name: communicationinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.communicationinfo (
    commid uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    type public."comm-type-enum" NOT NULL,
    value character varying NOT NULL,
    contactid uuid NOT NULL
);


ALTER TABLE public.communicationinfo OWNER TO postgres;

--
-- Name: addressinfo addressinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.addressinfo
    ADD CONSTRAINT addressinfo_pkey PRIMARY KEY (addressid);


--
-- Name: basicinfo basic-info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.basicinfo
    ADD CONSTRAINT "basic-info_pkey" PRIMARY KEY (contactid);


--
-- Name: communicationinfo communicationinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.communicationinfo
    ADD CONSTRAINT communicationinfo_pkey PRIMARY KEY (commid);


--
-- PostgreSQL database dump complete
--

