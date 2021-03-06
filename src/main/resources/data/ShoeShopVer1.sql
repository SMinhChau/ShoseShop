USE [master]
GO
/****** Object:  Database [ShoeShopVer1]    Script Date: 4/14/2022 1:00:22 PM ******/
CREATE DATABASE [ShoeShopVer1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ShoeShopVer1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\ShoeShopVer1.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ShoeShopVer1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\ShoeShopVer1_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [ShoeShopVer1] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ShoeShopVer1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ShoeShopVer1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET ARITHABORT OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ShoeShopVer1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ShoeShopVer1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ShoeShopVer1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ShoeShopVer1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ShoeShopVer1] SET  MULTI_USER 
GO
ALTER DATABASE [ShoeShopVer1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ShoeShopVer1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ShoeShopVer1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ShoeShopVer1] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ShoeShopVer1] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ShoeShopVer1] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [ShoeShopVer1] SET QUERY_STORE = OFF
GO
USE [ShoeShopVer1]
GO
USE [ShoeShopVer1]
GO
/****** Object:  Sequence [dbo].[hibernate_sequence]    Script Date: 4/14/2022 1:00:23 PM ******/
CREATE SEQUENCE [dbo].[hibernate_sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[address]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[address](
	[id] [bigint] NOT NULL,
	[city] [nvarchar](255) NOT NULL,
	[district] [nvarchar](255) NOT NULL,
	[ward] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brand]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brand](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [nvarchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [nvarchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[image] [varchar](255) NOT NULL,
	[name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_date] [datetime2](7) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[fullname] [nvarchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[phone] [varchar](20) NOT NULL,
	[status] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer_address]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer_address](
	[address_id] [bigint] NOT NULL,
	[customer_id] [bigint] NOT NULL,
	[detail] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[address_id] ASC,
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[evaluation]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[evaluation](
	[customer_id] [bigint] NOT NULL,
	[product_id] [bigint] NOT NULL,
	[comment] [nvarchar](255) NULL,
	[rate] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC,
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[order_id] [bigint] NOT NULL,
	[product_size_id] [bigint] NOT NULL,
	[price] [real] NOT NULL,
	[quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC,
	[product_size_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_date] [datetime2](7) NOT NULL,
	[status] [varchar](50) NOT NULL,
	[customer_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [nvarchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [nvarchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[avatar] [varchar](255) NULL,
	[name] [nvarchar](255) NOT NULL,
	[price] [real] NOT NULL,
	[promotion_price] [real] NOT NULL,
	[category_id] [bigint] NULL,
	[product_line_id] [bigint] NULL,
	[promotion_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_image]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_image](
	[product_id] [bigint] NOT NULL,
	[url] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC,
	[url] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_line]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_line](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [nvarchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [nvarchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[name] [varchar](255) NOT NULL,
	[brand_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_size]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_size](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[quantity] [int] NOT NULL,
	[product_id] [bigint] NULL,
	[size_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_tag]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_tag](
	[tag_id] [bigint] NOT NULL,
	[product_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC,
	[tag_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[promotion]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[promotion](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [nvarchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [nvarchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[begin_date] [datetime2](7) NOT NULL,
	[code] [varchar](50) NOT NULL,
	[end_date] [datetime2](7) NOT NULL,
	[percentile] [int] NOT NULL,
	[quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[size]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[size](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[slide_home]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[slide_home](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[image] [varchar](255) NOT NULL,
	[url] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tag]    Script Date: 4/14/2022 1:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tag](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_by] [nvarchar](255) NULL,
	[created_date] [datetime2](7) NULL,
	[modified_by] [nvarchar](255) NULL,
	[modified_date] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[brand] ON 

INSERT [dbo].[brand] ([id], [created_by], [created_date], [modified_by], [modified_date], [image], [name]) VALUES (1, NULL, NULL, NULL, NULL, N'adidas.png', N'Adidas')
INSERT [dbo].[brand] ([id], [created_by], [created_date], [modified_by], [modified_date], [image], [name]) VALUES (2, NULL, NULL, NULL, NULL, N'nike.png', N'Nike')
INSERT [dbo].[brand] ([id], [created_by], [created_date], [modified_by], [modified_date], [image], [name]) VALUES (3, NULL, NULL, NULL, NULL, N'mlb.png', N'MLB Korea')
INSERT [dbo].[brand] ([id], [created_by], [created_date], [modified_by], [modified_date], [image], [name]) VALUES (4, NULL, NULL, NULL, NULL, N'mcqueen.png', N'McQueen')
INSERT [dbo].[brand] ([id], [created_by], [created_date], [modified_by], [modified_date], [image], [name]) VALUES (5, NULL, NULL, NULL, NULL, N'converse.png', N'Converse')
INSERT [dbo].[brand] ([id], [created_by], [created_date], [modified_by], [modified_date], [image], [name]) VALUES (6, NULL, NULL, NULL, NULL, N'vans.png', N'VANS')
SET IDENTITY_INSERT [dbo].[brand] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (1, NULL, NULL, NULL, NULL, N'adidas-alphabounce-1.jpg', N'Adidas Alphabounce Beyond Xám 2018 Nam', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (2, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-blue-pink-2018-nu.jpg', N'Adidas Alphabounce Beyond Blue Pink Nữ 2018', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (3, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-den-de-trang-2019-nam.jpg', N'Adidas Alphabounce Beyond Đen Đỏ Trắng 2019 Nam', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (4, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-den-full-2018-nam-nu.jpg', N'Adidas Alphabounce Beyond Đen Full 2018 Nam, Nữ', 1080000, 500000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (5, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-den-full-2019-nam.jpg', N'Adidas Alphabounce Beyond Đen Full 2019 Nam', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (6, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-den-trang-2018-nam-nu.jpg', N'Adidas Alphabounce Beyond Đen Trắng Nam, Nữ 2018', 1080000, 500000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (7, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-den-vang-2018.jpg', N'Adidas Alphabounce Beyond Đen Vàng 2018 Nam', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (8, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-do-2018-nam-nu.jpg', N'Adidas Alphabounce Beyond Đỏ Nam, Nữ 2018', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (9, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-kem-do-2018-nu.jpg', N'Adidas Alphabounce Beyond Kem Cam 2018 Nam', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (10, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-kem-trang-2018-nu.jpg', N'Adidas Alphabounce Beyond Kem Tím 2018 Nữ', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (11, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-mar0on-do-hong-2018.jpg', N'Adidas alphabounce beyond maroon Đỏ hồng 2018', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (12, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-trang-full-2019-nam-nu.jpg', N'Adidas Alphabounce Beyond Trắng Full 2019 Nam, Nữ', 1080000, 500000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (13, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-trang-full-nam-nu-2018.jpg', N'Adidas Alphabounce Beyond Trắng Full Nam, Nữ 2018', 1080000, 500000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (14, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-trang-muoi-tieu-2018-nam-nu.jpg', N'Adidas Alphabounce Beyond Trắng Muối Tiêu Nam, Nữ 2018', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (15, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-xam-2019-nam.jpg', N'Adidas Alphabounce Beyond Xám 2019 Nam', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (16, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-xam-den-2018-nam-nu.jpg', N'Adidas Alphabounce Beyond Xám Đen 2018 Nam, Nữ', 1080000, 500000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (17, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-xam-do-2018.jpg', N'Adidas Alphabounce Beyond Xám Đỏ 2018 Nam, Nữ', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (18, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-xam-trang-muoi-tieu-2018-nam.jpg', N'Adidas Alphabounce Beyond 2018 Xám Trắng Muối Tiêu Nam', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (19, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-xanh-cam-2018-nam.jpg', N'Adidas Alphabounce Beyond 2018 Xanh Cam Nam', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (20, NULL, NULL, NULL, NULL, N'adidas-alphabounce-beyond-xanh-duong-2019-nam-nu.jpg', N'Adidas Alphabounce Beyond Xanh Dương 2019 Nam, Nữ', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (21, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-den-de-do.jpg', N'Adidas Alphabounce Instinct Đen Đỏ Đỏ', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (22, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-den-full-2018.jpg', N'Adidas alphabounce instinct black full 2018', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (23, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-den-trang-2018.jpg', N'Adidas alphabounce instinct Đen trắng 2018', 1100000, 500000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (24, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-greyish-nam-nu.jpg', N'Adidas Alphabounce Instinct Greyish Nam Nữ', 720000, 600000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (25, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-kem-nau-2018-nu.jpg', N'Adidas alphabounce instinct kem nâu 2018 nam nữ', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (26, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-m-core-black-1-1.jpg', N'Adidas Alphabounce Instinct M Core Black 1:1', 1320000, 600000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (27, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-m-full-black-1-1.jpg', N'Adidas Alphabounce Instinct M Full Black 1:1', 1320000, 1100000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (28, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-m-grey-red-1-1.jpg', N'Adidas Alphabounce Instinct M Grey Red 1:1', 1320000, 1100000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (29, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-m-grey-silver.jpg', N'Adidas Alphabounce Instinct M Grey Silver 1:1', 1320000, 600000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (30, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-m-orange-blue-1-1.jpg', N'Adidas Alphabounce Instinct M Orange Blue 1:1', 1320000, 600000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (31, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-m-turquoise.jpg', N'Adidas Alphabounce Instinct M Turquoise 1:1', 1320000, 600000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (32, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-m-white-red-blue-1-1.jpg', N'Adidas Alphabounce Instinct M White Red Blue 1:1', 1320000, 600000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (33, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-trang-2018.jpg', N'Adidas alphabounce instinct trắng xám 2018 nam nữ', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (34, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-trang-2018-nam-nu.jpg', N'Adidas alphabounce instinct trắng full 2018 nam nữ', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (35, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-xam-2018.jpg', N'Adidas alphabounce instinct xám 2018 nam nữ', 900000, 500000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (36, NULL, NULL, NULL, NULL, N'adidas-alphabounce-instinct-xam-bac.jpg', N'Adidas Alphabounce Instinct Xám Bạc', 1080000, 900000, NULL, 6, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (37, NULL, NULL, NULL, NULL, N'adidas-equipment-plus-full-black.jpg', N'Adidas Equipment Plus Full Black', 1200000, 1000000, NULL, 2, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (38, NULL, NULL, NULL, NULL, N'adidas-equipment-plus-white-cloud-pink.jpg', N'Adidas Equipment Plus White Cloud Pink', 1200000, 1000000, NULL, 2, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (39, NULL, NULL, NULL, NULL, N'adidas-equipment-plus-white-gold-pink.jpg', N'Adidas Equipment Plus White Gold Pink', 1200000, 1000000, NULL, 2, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (40, NULL, NULL, NULL, NULL, N'adidas-equipment-plus-white-navy-orange.jpg', N'Adidas Equipment Plus White Navy Orange', 1200000, 1000000, NULL, 2, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (41, NULL, NULL, NULL, NULL, N'adidas-equipment-plus-white-turquoise-black.jpg', N'Adidas Equipment Plus White Turquoise Black', 1200000, 1000000, NULL, 2, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (42, NULL, NULL, NULL, NULL, N'adidas-forum-low-all-white.jpg', N'Adidas Forum Low All White', 1200000, 850000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (43, NULL, NULL, NULL, NULL, N'adidas-forum-low-white-green.jpg', N'Adidas Forum Low White Green', 1200000, 850000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (44, NULL, NULL, NULL, NULL, N'adidas-nmd-humanrace-hong-nu-replica-1-1.jpg', N'Adidas Nmd Humanrace Hồng Nữ Replica 1:1', 1680000, 800000, NULL, 10, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (45, NULL, NULL, NULL, NULL, N'adidas-nmd-humanrace-vang-nam-nu-replica-1-1.jpg', N'Adidas Nmd Humanrace Vàng Nam, Nữ Replica 1:1', 1680000, 800000, NULL, 10, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (46, NULL, NULL, NULL, NULL, N'adidas-nmd-humanrace-xanh-nam-nu-replica-1-1.jpg', N'Adidas Nmd Humanrace Xanh Nam, Nữ Replica 1:1', 1680000, 800000, NULL, 10, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (47, NULL, NULL, NULL, NULL, N'adidas-ozweego-beige-light-brown-pale-nude.jpg', N'Adidas Ozweego ''Beige'' Light Brown/Pale Nude 1:1', 840000, 700000, NULL, 11, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (48, NULL, NULL, NULL, NULL, N'adidas-ozweego-core-black-onix-1-1.jpg', N'Adidas Ozweego Core Black/Onix 1:1', 840000, 700000, NULL, 11, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (49, NULL, NULL, NULL, NULL, N'adidas-ozweego-icey-pink-trace-maroon-1-1.jpg', N'Adidas Ozweego Icey Pink/Trace Maroon 1:1', 840000, 700000, NULL, 11, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (50, NULL, NULL, NULL, NULL, N'adidas-ozweego-trace-cargo-green-1-1.jpg', N'Adidas Ozweego Trace Cargo Green 1:1', 840000, 500000, NULL, 11, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (51, NULL, NULL, NULL, NULL, N'adidas-prophere-den-cam-nam-nu.jpg', N'Adidas Prophere Đen Cam Nam, Nữ', 960000, 450000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (52, NULL, NULL, NULL, NULL, N'adidas-prophere-den-full-nam-nu.jpg', N'Adidas Prophere Đen Full Nam, Nữ', 960000, 600000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (53, NULL, NULL, NULL, NULL, N'adidas-prophere-den-nam-nu.jpg', N'Adidas Prophere Đen Trắng Nam, Nữ', 960000, 800000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (54, NULL, NULL, NULL, NULL, N'adidas-prophere-den-trang-de-den-nam.jpg', N'Adidas Prophere Oreo Pack Nam, Nữ', 960000, 450000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (55, NULL, NULL, NULL, NULL, N'adidas-prophere-den-vang-nam-nu.jpg', N'Adidas Prophere Đen Vàng Nam, Nữ', 960000, 450000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (56, NULL, NULL, NULL, NULL, N'adidas-prophere-do-do-nam-nu.jpg', N'Adidas Prophere Đỏ Đỏ Nam, Nữ', 960000, 600000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (57, NULL, NULL, NULL, NULL, N'adidas-prophere-orange-brown-nu.jpg', N'Adidas Prophere Orange Brown Nữ', 960000, 600000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (58, NULL, NULL, NULL, NULL, N'adidas-prophere-reu-cam-nam-nu.jpg', N'Adidas Prophere Rêu Cam Nam, Nữ', 960000, 600000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (59, NULL, NULL, NULL, NULL, N'adidas-prophere-trang-full-nam-nu.jpg', N'Adidas Prophere Trắng Full Nam, Nữ', 960000, 600000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (60, NULL, NULL, NULL, NULL, N'adidas-prophere-trang-nam-nu.jpg', N'Adidas Prophere Trắng Hồng Nam, Nữ', 960000, 800000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (61, NULL, NULL, NULL, NULL, N'adidas-prophere-vapour-grey-nam-nu.jpg', N'Adidas Prophere Vapour Grey Nam Nữ', 960000, 800000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (62, NULL, NULL, NULL, NULL, N'adidas-prophere-xam-cam-nam-nu.jpg', N'Adidas Prophere Xám Cam Nam Nữ', 960000, 450000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (63, NULL, NULL, NULL, NULL, N'adidas-prophere-xanh-duong.jpg', N'Adidas Prophere Xanh Dương Nam, Nữ', 960000, 450000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (64, NULL, NULL, NULL, NULL, N'adidas-prophere-xanh-ngoc-clear-mint-nu.jpg', N'Adidas Prophere Xanh Ngọc - Clear Mint Nữ', 960000, 800000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (65, NULL, NULL, NULL, NULL, N'adidas-prophere-xanh-ngoc-nu.jpg', N'Adidas Prophere Xanh Ngọc Nữ', 960000, 800000, NULL, 8, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (66, NULL, NULL, NULL, NULL, N'adidas-superstar-den-soc-trang-nam-nu.jpg', N'ADIDAS SUPERSTAR Đen SỌC TRẮNG NAM, Nữ', 660000, 550000, NULL, 9, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (67, NULL, NULL, NULL, NULL, N'adidas-superstar-trang-full-nam-nu.jpg', N'Adidas Superstar Trắng Full Nam Nữ', 720000, 600000, NULL, 9, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (68, NULL, NULL, NULL, NULL, N'adidas-superstar-trang-got-xanh-la-nam-nu-1-1.jpg', N'Adidas Superstar Trắng Gót Xanh Lá Nam Nữ 1:1', 1680000, 900000, NULL, 9, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (69, NULL, NULL, NULL, NULL, N'adidas-superstar-trang-soc-den.jpg', N'Adidas Superstar Trắng Sọc Đen Nam Nữ', 780000, 650000, NULL, 9, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (70, NULL, NULL, NULL, NULL, N'adidas-superstar-trang-soc-hong-nu.jpg', N'Adidas Super Star Trắng Sọc Hồng Nữ', 660000, 550000, NULL, 9, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (71, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-19-5-0-all-black-1-1.jpg', N'Adidas Ultra Boost 19 (5.0) All Black 1:1', 1380000, 1150000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (72, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-cloud-white-easy-blue.jpg', N'Adidas Ultra Boost 20 Cloud White / Easy Blue 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (73, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-consortium-cloud-white-blue-red.jpg', N'Adidas Ultra Boost 20 Consortium CLoud White Blue Red 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (74, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-consotium-all-white-reflective.jpg', N'Adidas Ultra Boost 20 Consotium All White Reflective 1:1', 1500000, 800000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (75, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-consotium-black-white-1-1.jpg', N'Adidas Ultra Boost 20 Consotium Black White 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (76, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-consotium-black-white-reflective-1-1.jpg', N'Adidas Ultra Boost 20 Consotium Black White Reflective 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (77, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-consotium-triple-white.jpg', N'Adidas Ultra Boost 20 Consotium Triple White 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (78, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-core-black-1-1.jpg', N'Adidas Ultra Boost 20 Consortium Core Black 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (79, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-cream-orange-pattern-logo.jpg', N'Adidas Ultra Boost 20 Cream Orange Pattern Logo 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (80, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-dash-grey-1-1.jpg', N'Adidas ultra boost 20 consortium dash grey 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (81, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-space-race-blue-1-1.jpg', N'Adidas ultra boost 20 consortium space race blue 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (82, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-space-race-grey-1-1.jpg', N'Adidas ultra boost 20 consortium space race grey 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (83, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-triple-black-nam-nu-1-1.jpg', N'Adidas Ultra Boost 20 Triple Black 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (84, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-20-white-stripes-black.jpg', N'Adidas Ultra Boost 20 White Stripes Black 1:1', 1500000, 1250000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (85, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-all-black-triple-black.jpg', N'Adidas Ultra Boost 21 All Black / Triple Black', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (86, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-all-white-triple-white.jpg', N'Adidas Ultra Boost 21 All White / Triple White', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (87, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-core-black.jpg', N'Adidas Ultra Boost 21 Core Black', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (88, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-core-black-screaming-orange.jpg', N'Adidas Ultra Boost 21 Core Black / Screaming Orange', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (89, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-core-black-solar-yellow.jpg', N'Adidas Ultra Boost 21 Core Black / Solar Yellow', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (90, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-core-black-sub-green.jpg', N'Adidas Ultra Boost 21 Core Black / Sub Green', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (91, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-crew-navy.jpg', N'Adidas Ultra Boost 21 Crew Navy', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (92, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-crystal-cloud-white-glow-pink.jpg', N'Adidas Ultra Boost 21 Crystal Cloud White / Glow Pink', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (93, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-crystal-white.jpg', N'Adidas Ultra Boost 21 Crystal White', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (94, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-dash-grey-screaming-orange.jpg', N'Adidas Ultra Boost 21 Dash Grey / Screaming Orange', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (95, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-grey-five-screaming-orange.jpg', N'Adidas Ultra Boost 21 Grey Five / Screaming Orange', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (96, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-grey-three.jpg', N'Adidas Ultra Boost 21 Grey Three', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (97, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-halo-silver-grey-two.jpg', N'Adidas Ultra Boost 21 Halo Silver / Grey Two', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (98, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-white-grey-dark-blue-yellow.jpg', N'Adidas Ultra Boost 21 White Grey / Dark Blue Yellow', 1560000, 1300000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (99, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-21-white-stripes-black.jpg', N'Adidas Ultra Boost 21 White Stripes Black', 1560000, 1300000, NULL, 4, NULL)
GO
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (100, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-4-0-clima-trang-nam-nu-rep-1-1.jpg', N'Adidas ultra boost 4.0 Clima trắng nam, nữ rep 1:1', 1200000, 650000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (101, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-4-0-den-da-nam-nu-rep-1-1.jpg', N'Adidas ultra boost 4.0 Clima Đen nam, nữ rep 1:1', 1200000, 650000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (102, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-4-0-den-de-trang-nam-nu.jpg', N'Adidas ultra boost 4.0 Đen Đỏ trắng nam, nữ', 900000, 500000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (103, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-4-0-game-of-thrones-house-stark-nam-1-1.jpg', N'Adidas Ultra Boost 4.0 Game of Thrones House Stark nam 1:1', 1080000, 900000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (104, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-5-0-black-multi-color-rep-1-1-2019.jpg', N'Adidas ultra boost 5.0 black multi-color rep 1:1 2019', 960000, 600000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (105, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-5-0-den-de-trang-rep-1-1-2019.jpg', N'Adidas ultra boost 5.0 Đen Đỏ trắng rep 1:1 2019', 960000, 600000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (106, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-5-0-hong-rep-1-1-2019.jpg', N'Adidas ultra boost 5.0 hồng rep 1:1', 960000, 800000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (107, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-5-0-trang-den-do-rep-1-1-2019.jpg', N'Adidas ultra boost 5.0 trắng Đen Đỏ rep 1:1 2019', 960000, 800000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (108, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-5-0-trang-do-do.jpg', N'Adidas ultra boost 4.0 trắng Đỏ Đỏ nam, nữ', 900000, 500000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (109, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-5-0-white-multi-color-1-1-2019.jpg', N'Adidas ultra boost 5.0 white multi-color 1:1 2019', 960000, 800000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (110, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-den-full-1-1.jpg', N'Adidas Ultra Boost 4.0 Đen full 1:1 nam, nữ', 1200000, 800000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (111, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-den-trang-nam-nu-2019.jpg', N'Adidas ultra boost 5.0 Đen trắng nam, nữ 2019', 720000, 600000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (112, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-den-trang-nam-nu-rep-1-1.jpg', N'Adidas ultra boost 4.0 Đen trắng nam, nữ rep 1:1', 1200000, 800000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (113, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-nau-nam-nu-2019.jpg', N'Adidas ultra boost 5.0 nâu nam, nữ 2019', 900000, 750000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (114, NULL, NULL, NULL, NULL, N'adidas-ultra-boost-xam-trang-nam-nu-rep-1-1.jpg', N'Adidas ultra boost 4.0 xám trắng nam, nữ rep 1:1', 1200000, 800000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (115, NULL, NULL, NULL, NULL, N'adidas-x9000l4-core-black-signal-orange.jpg', N'Adidas X9000L4 Core Black / Signal Orange', 1300000, 1000000, NULL, 1, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (116, NULL, NULL, NULL, NULL, N'adidas-x9000l4-grey-two-night-metallic.jpg', N'Adidas X9000L4 Grey Two / Night Metallic', 1300000, 1000000, NULL, 1, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (117, NULL, NULL, NULL, NULL, N'adidas-x9000l4-triple-white.jpg', N'Adidas X9000L4 Triple White', 1300000, 1000000, NULL, 1, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (118, NULL, NULL, NULL, NULL, N'adidas-yeezy-350-v2-cloud-white-reflective-nam-nu-1-1.jpg', N'Adidas Yeezy 350 V2 Cloud White Reflective Nam Nữ', 1680000, 1400000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (119, NULL, NULL, NULL, NULL, N'adidas-yeezy-350-v2-israfil.jpg', N'Adidas Yeezy 350 V2 ''Israfil''', 1320000, 1100000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (120, NULL, NULL, NULL, NULL, N'adidas-yeezy-350-v2-mono-clay-1-1.jpg', N'Adidas Yeezy 350 V2 ''Mono Clay''', 1450000, 1200000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (121, NULL, NULL, NULL, NULL, N'adidas-yeezy-350-v2-mono-ice-1-1.jpg', N'Adidas Yeezy 350 V2 ''Mono Ice''', 1450000, 1200000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (122, NULL, NULL, NULL, NULL, N'adidas-yeezy-350-v2-sand-taupe-1-1.jpg', N'Adidas Yeezy 350 V2 ''Sand Taupe''', 1500000, 1200000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (123, NULL, NULL, NULL, NULL, N'adidas-yeezy-350-v2-static-black-reflective-shoelace-1-1.jpg', N'Adidas Yeezy 350 V2 Static Black ''Reflective Shoelace''', 1700000, 1400000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (124, NULL, NULL, NULL, NULL, N'adidas-yeezy-350-v2-tail-light-tailgate-nam-nu-1-1.jpg', N'Adidas Yeezy 350 V2 Tail Light (Tailgate) Nam Nữ', 1320000, 1100000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (125, NULL, NULL, NULL, NULL, N'adidas-yeezy-500-den-full-utility-black-nam-nu.jpg', N'ADIDAS YEEZY 500 Đen M? - UTILITY BLACK NAM, Nữ', 1320000, 1100000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (126, NULL, NULL, NULL, NULL, N'adidas-yeezy-500-trang-full-blush-nam-nu.jpg', N'ADIDAS YEEZY 500 TRẮNG FULL - BLUSH NAM, Nữ', 1320000, 1100000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (127, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-boost-magnet-nam-nu-1-1.jpg', N'Adidas Yeezy 700 Boost Magnet Nam, Nữ', 1320000, 900000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (128, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-den-full-nam-nu-rep-1-1.jpg', N'Adidas Yeezy 700 Vanta Đen Full', 1440000, 1200000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (129, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-inertia-xam-xanh-nam-nu-1-1.jpg', N'Adidas Yeezy 700 Inertia Xám Xanh', 1320000, 1100000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (130, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-mauve-nam-nu.jpg', N'Adidas Yeezy 700 Mauve', 1320000, 800000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (131, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-mauve-nam-nu-sf.jpg', N'Adidas Yeezy 700 mauve nam, nữ sf', 900000, 500000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (132, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-v2-geode-nam-nu-1-1.jpg', N'Adidas Yeezy 700 V2 Geode', 1320000, 1100000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (133, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-v2-static-trang-bac-nam-nu.jpg', N'Adidas Yeezy 700 V2 Static Trắng Bạc Nam, Nữ', 1320000, 950000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (134, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-v3-azael-nam-nu.jpg', N'Adidas Yeezy 700 V3 ''Azael'' Nam Nữ', 1140000, 950000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (135, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-v3-vang-kem-nam-nu.jpg', N'Adidas Yeezy 700 V3 Vàng Kem Nam Nữ', 1140000, 950000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (136, NULL, NULL, NULL, NULL, N'adidas-yeezy-700-wave-trang-xam-nam-nu.jpg', N'Adidas Yeezy 700 OG Wave Runner', 1320000, 900000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (137, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-350-v2-beluga-1-0-nam-nu-1-1.jpg', N'Adidas Yeezy boost 350 V2 Beluga 1.0 nam, nữ', 1200000, 1000000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (138, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-350-v2-cream-white-nam-nu-rep-1-1.jpg', N'Adidas Yeezy boost 350 V2 cream white', 1200000, 1000000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (139, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-350-v2-lundmark-3m-reflective-nam-nu-1-1.jpg', N'Adidas Yeezy boost 350 V2 Lundmark 3m ''reflective'' nam, nữ', 1680000, 1400000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (140, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-350-v2-static-black-3m-reflective-nam-nu.jpg', N'Adidas Yeezy Boost 350 V2 Static Black 3m ''Reflective'' nam, nữ', 1680000, 1400000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (141, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-350-v2-static-nam-nu.jpg', N'Adidas Yeezy boost 350 V2 static', 1680000, 1400000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (142, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-350-v2-static-nam-nu-rep-1-1-full-phan-quang.jpg', N'Adidas Yeezy 350 V2 Static nam, nữ rep 1:1 full phản quang', 1680000, 1400000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (143, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-380-alien.jpg', N'Adidas Yeezy Boost 380 ''Alien''', 1850000, 1500000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (144, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-380-alien-blue.jpg', N'Adidas Yeezy Boost 380 ''Alien Blue''', 1850000, 1500000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (145, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-380-mist.jpg', N'Adidas Yeezy Boost 380 ''Mist''', 1850000, 1500000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (146, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-380-onyx-non-reflective.jpg', N'Adidas Yeezy Boost 380 ''OnyX'' Non-Reflective', 1850000, 1500000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (147, NULL, NULL, NULL, NULL, N'adidas-yeezy-boost-380-pyrite.jpg', N'Adidas Yeezy Boost 380 ''Pyrite''', 1850000, 1500000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (148, NULL, NULL, NULL, NULL, N'adidas-yeezy-slide-bone.jpg', N'Adidas Yeezy Slide Bone', 800000, 600000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (149, NULL, NULL, NULL, NULL, N'adidas-yeezy-slide-desert-sand.jpg', N'Adidas Yeezy Slide Desert Sand', 800000, 600000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (150, NULL, NULL, NULL, NULL, N'adidas-yeezy-slide-kaws-supreme.jpg', N'Adidas Yeezy Slide Kaws Supreme', 800000, 600000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (151, NULL, NULL, NULL, NULL, N'adidas-yeezy-slide-triple-black.jpg', N'Adidas Yeezy Slide Triple Black', 800000, 600000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (152, NULL, NULL, NULL, NULL, N'adidas-yung-1-barber-shop-nam-nu.jpg', N'Adidas Yung 1 Barber Shop Nam Nữ', 960000, 500000, NULL, 12, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (153, NULL, NULL, NULL, NULL, N'adidas-yung-cam-songoku-nam-nu.jpg', N'ADIDAS YUNG CAM SONGOKU NAM, Nữ', 720000, 600000, NULL, 12, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (154, NULL, NULL, NULL, NULL, N'adidas-yung-cream-black-pink-nu.jpg', N'Adidas Yung Cream Black Pink Nữ', 960000, 400000, NULL, 12, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (155, NULL, NULL, NULL, NULL, N'adidas-yung-do-nam-nu.jpg', N'Adidas Yung 1 Đỏ Nam Nữ', 960000, 800000, NULL, 12, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (156, NULL, NULL, NULL, NULL, N'adidas-yung-trang-nam-nu.jpg', N'ADIDAS YUNG TRẮNG NAM, Nữ', 720000, 600000, NULL, 12, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (157, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-clear-grey-core-black-granite.jpg', N'Adidas ZX 2K Boost Clear Grey Core Black Granite', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (158, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-cloud-white-blue-bird.jpg', N'Adidas ZX 2K Boost Cloud White Blue Bird', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (159, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-cloud-white-pink-purple.jpg', N'Adidas ZX 2K Boost Cloud White Pink Purple', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (160, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-cloud-white-pink-tint.jpg', N'Adidas ZX 2K Boost Cloud White Pink Tint', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (161, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-cloud-white-supplier-colour.jpg', N'Adidas ZX 2K Boost Cloud White Supplier Colour', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (162, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-cream-black-orange-1-1.jpg', N'Adidas ZX 2K Boost Cream Black Orange', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (163, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-crew-blue-yellow-core-black.jpg', N'Adidas ZX 2K Boost Crew Blue Yellow Core Black', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (164, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-grey-two-signal-cyan.jpg', N'Adidas ZX 2K Boost Grey Two Signal Cyan', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (165, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-ninja-time-in-cloud-white-1-1.jpg', N'Adidas ZX 2K Boost Ninja Time In Cloud White', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (166, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-white-blue-red-1-1.jpg', N'Adidas ZX 2K Boost White Blue Red', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (167, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-white-cream-brown-navy.jpg', N'Adidas ZX 2K Boost White Cream Brown Navy', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (168, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-white-multi-color-1-1.jpg', N'Adidas ZX 2K Boost White Multi-color', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (169, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-white-solar-gold.jpg', N'Adidas ZX 2K Boost White Solar Gold', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (170, NULL, NULL, NULL, NULL, N'adidas-zx-2k-boost-white-solar-red-blue.jpg', N'Adidas ZX 2K Boost White Solar Red Blue', 1200000, 1000000, NULL, 3, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (171, NULL, NULL, NULL, NULL, N'ben-jerry-s-x-nike-sb-dunk-low-chunky-dunky.jpg', N'Nike SB Dunk x Ben & Jerry''s Low Chunky Dunky', 1080000, 850000, NULL, NULL, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (172, NULL, NULL, NULL, NULL, N'converse-1970s-x-g-dragon-nam-nu-1-1.jpg', N'Converse 1970s x G Dragon nam, nữ 1:1', 1020000, 850000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (173, NULL, NULL, NULL, NULL, N'converse-chuck-1970-chinatown-market-uv-doi-mau.jpg', N'Converse Chuck 1970 Chinatown Market UV Đỏi màu nam nữ', 1020000, 550000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (174, NULL, NULL, NULL, NULL, N'converse-chuck-1970-den-co-thap-nam-nu.jpg', N'Converse Chuck 1970 Đen Cổ Th?p Nam, Nữ', 850000, 700000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (175, NULL, NULL, NULL, NULL, N'converse-chuck-1970-nau-do-co-cao-nam-nu.jpg', N'Converse Chuck 1970 Nâu Đỏ Cổ Cao Nam, Nữ', 850000, 500000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (176, NULL, NULL, NULL, NULL, N'converse-chuck-1970-trang-co-cao-nam-nu.jpg', N'Converse Chuck 1970 Trắng Cổ Cao Nam, Nữ', 850000, 700000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (177, NULL, NULL, NULL, NULL, N'converse-chuck-1970-trang-co-thap-nam-nu.jpg', N'Converse Chuck 1970 Trắng Cổ Th?p Nam, Nữ', 850000, 500000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (178, NULL, NULL, NULL, NULL, N'converse-den-co-cao-nam-nu.jpg', N'Converse Chuck 1970 Đen Cổ Cao Nam, Nữ', 850000, 700000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (179, NULL, NULL, NULL, NULL, N'converse-vang-co-cao-nam-nu.jpg', N'Converse Chuck 1970 Vàng Cổ Cao Nam, Nữ', 850000, 500000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (180, NULL, NULL, NULL, NULL, N'converse-vang-co-thap-nam-nu.jpg', N'Converse Chuck 1970 Vàng Cổ Th?p Nam, Nữ', 850000, 500000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (181, NULL, NULL, NULL, NULL, N'giay-adidas-trang-full-1-1.jpg', N'Adidas ultra boost 4.0 trắng full 1:1 nam, nữ', 1200000, 800000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (182, NULL, NULL, NULL, NULL, N'giay-adidas-ultra-boost-hong.jpg', N'Adidas Ultra Boost 4.0 hồng nữ', 720000, 600000, NULL, 4, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (183, NULL, NULL, NULL, NULL, N'mcqueen-hong-trang-nu-1-1.jpg', N'Mcqueen Hồng Trắng Nữ 1:1', 1320000, 750000, NULL, 22, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (184, NULL, NULL, NULL, NULL, N'mcqueen-trang-full-phan-quang-nam-nu.jpg', N'Mcqueen Static Phản Quang Nam Nữ', 1680000, 900000, NULL, 22, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (185, NULL, NULL, NULL, NULL, N'mcqueen-trang-got-bong-tim-1-1-nam-nu.jpg', N'Mcqueen trắng gót bóng tím 1:1 nam, nữ', 1320000, 1100000, NULL, 22, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (186, NULL, NULL, NULL, NULL, N'mcqueen-trang-got-bong-vang-1-1-nam-nu.jpg', N'Mcqueen trắng gót bóng vàng 1:1 nam, nữ', 1320000, 800000, NULL, 22, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (187, NULL, NULL, NULL, NULL, N'mcqueen-trang-got-den-1-1-nam-nu.jpg', N'Mcqueen trắng gót Đen 1:1 nam, nữ', 1320000, 1100000, NULL, 22, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (188, NULL, NULL, NULL, NULL, N'mcqueen-trang-got-den-nhung-nam-nu.jpg', N'Mcqueen trắng gót Đen ''nhung'' nam nữ', 1500000, 1300000, NULL, 22, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (189, NULL, NULL, NULL, NULL, N'mcqueen-trang-got-hong-nhung-nu.jpg', N'Mcqueen Trắng Gót Hồng ''Nhung'' Nữ', 1320000, 1100000, NULL, 22, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (190, NULL, NULL, NULL, NULL, N'mcqueen-trang-got-vang-1-1-nam-nu.jpg', N'Mcqueen trắng gót vàng 1:1 nam, nữ', 1320000, 750000, NULL, 22, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (191, NULL, NULL, NULL, NULL, N'nike-air-force-1-da-lon-xam-nam.jpg', N'Nike Air Force 1 ''Reigning Champ'' Da Lộn Xám Nam 1:1', 1420000, 1200000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (192, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-07-lx-lucky-charm-black.jpg', N'Nike Air Force 1 Low 07 LX Lucky Charm Black', 1200000, 950000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (193, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-07-lx-lucky-charm-white.jpg', N'Nike Air Force 1 Low 07 LX Lucky Charm White', 1200000, 950000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (194, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-brooklyn-cream.jpg', N'Nike Air Force 1 Low ''Brooklyn'' Cream', 1200000, 1000000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (195, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-lv8-gs-double-swoosh.jpg', N'Nike Air Force 1 Low LV8 GS ''Double Swoosh''', 1280000, 1000000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (196, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-mca-blue-off-white-1-1.jpg', N'Nike Air Force 1 Low MCA Blue Off White 1:1', 1140000, 950000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (197, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-paisley-blue.jpg', N'Nike Air Force 1 Low ''Paisley Blue''', 1200000, 1000000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (198, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-stussy-fossil.jpg', N'Nike Air Force 1 Low Stussy Fossil', 1200000, 1000000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (199, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-stussy-fossil-black.jpg', N'Nike Air Force 1 Low Stussy Fossil Black', 1200000, 1000000, NULL, 15, NULL)
GO
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (200, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-white-brown.jpg', N'Nike Air Force 1 Low White Brown', 1200000, 950000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (201, NULL, NULL, NULL, NULL, N'nike-air-force-1-low-white-swoosh-purple.jpg', N'Nike Air Force 1 Low White Swoosh Purple', 1200000, 900000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (202, NULL, NULL, NULL, NULL, N'nike-air-force-1-shadow-cashmere.jpg', N'Nike Air Force 1 Shadow ''Cashmere''', 1200000, 1000000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (203, NULL, NULL, NULL, NULL, N'nike-air-force-1-trang-full-nam-nu.jpg', N'Nike Air Force 1 Trắng Full Nam, Nữ', 1100000, 800000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (204, NULL, NULL, NULL, NULL, N'nike-air-force-1-white-black.jpg', N'Nike Air Force 1 White Swoosh Black', 1100000, 900000, NULL, 15, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (205, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-gs-paint-drip-1-1.jpg', N'Nike Air Jordan 1 GS ''Paint Drip''', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (206, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-high-electro-orange.jpg', N'Nike Air Jordan 1 High Electro Orange', 1320000, 1100000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (207, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-high-japan-neutral-grey.jpg', N'Nike Air Jordan 1 High ''Japan Neutral'' Grey', 1320000, 1100000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (208, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-high-travis-scott-x-fragment.jpg', N'Nike Air Jordan 1 High Travis Scott x Fragment', 1320000, 1100000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (209, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-high-university-blue-pk-god.jpg', N'Nike Air Jordan 1 High ''University Blue'' Pk God', 1600000, 1400000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (210, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-atmosphere-grey-black-1-1.jpg', N'Nike Air Jordan 1 Low ''Atmosphere'' Grey Black', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (211, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-black-toe.jpg', N'Nike Air Jordan 1 Low ''Black Toe''', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (212, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-court-purple-black-1-1.jpg', N'Nike Air Jordan 1 Low ''Court'' Purple Black', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (213, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-gym-red.jpg', N'Nike Air Jordan 1 Low ''Gym Red''', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (214, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-lakers-yellow-white-black-purple-1-1.jpg', N'Nike Air Jordan 1 Low ''Lakers'' Yellow White Black Purple', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (215, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-light-smoke-grey-1-1.jpg', N'Nike Air Jordan 1 Low Light Smoke Grey', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (216, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-pine-green-1-1.jpg', N'Nike Air Jordan 1 Low ''Pine'' Green', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (217, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-shattered-backboard.jpg', N'Nike Air Jordan 1 Low ''Shattered Backboard''', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (218, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-smoke-grey-v3.jpg', N'Nike Air Jordan 1 Low Smoke Grey V3', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (219, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-travis-scott-reverse-mocha.jpg', N'Nike Air Jordan 1 Low Travis Scott ''Reverse Mocha''', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (220, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-travis-scott-x-fragment.jpg', N'Nike Air Jordan 1 Low Travis Scott x Fragment', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (221, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-white-wolf-grey.jpg', N'Nike Air Jordan 1 Low ''White Wolf Grey''', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (222, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-low-x-dior-grey-1-1.jpg', N'Nike Air Jordan 1 Low x Dior Grey', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (223, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-mid-chile-red.jpg', N'Nike Air Jordan 1 Mid ''Chile Red''', 1320000, 1100000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (224, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-mid-diamonds-short.jpg', N'Nike Air Jordan 1 Mid ''Diamonds'' Short', 1320000, 1100000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (225, NULL, NULL, NULL, NULL, N'nike-air-jordan-1-white-university-red-1-1.jpg', N'Nike Air Jordan 1 Low ''White University Red''', 1200000, 1000000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (226, NULL, NULL, NULL, NULL, N'nike-air-jordan-4-bred.jpg', N'Nike Air Jordan 4 ''Bred''', 1500000, 1200000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (227, NULL, NULL, NULL, NULL, N'nike-air-jordan-4-off-white-ow-pk-god.jpg', N'Nike Air Jordan 4 Off White (OW) Pk God', 1850000, 1500000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (228, NULL, NULL, NULL, NULL, N'nike-air-jordan-4-oreo-pk-god.jpg', N'Nike Air Jordan 4 ''Oreo'' PK God', 1850000, 1500000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (229, NULL, NULL, NULL, NULL, N'nike-air-jordan-4-travis-pk-god.jpg', N'Nike Air Jordan 4 ''Travis Scott'' PK God', 1850000, 1500000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (230, NULL, NULL, NULL, NULL, N'nike-air-jordan-4-university-blue.jpg', N'Nike Air Jordan 4 ''University Blue''', 1500000, 1200000, NULL, 14, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (231, NULL, NULL, NULL, NULL, N'nike-dunk-low-coast.jpg', N'Nike Dunk Low Coast', 1100000, 950000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (232, NULL, NULL, NULL, NULL, N'nike-kwondo1-x-peaceminusone-reflective.jpg', N'Nike Kwondo1 x Peaceminusone', 1300000, 1100000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (233, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-black-white.jpg', N'Nike SB Dunk Low BLack White', 1140000, 950000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (234, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-chicago.jpg', N'Nike SB Dunk Low ''Chicago''', 1080000, 900000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (235, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-laser-orange.jpg', N'Nike SB Dunk Low ''Laser Orange''', 1080000, 900000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (236, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-orange-pearl.jpg', N'Nike SB Dunk Low Orange Pearl', 1100000, 950000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (237, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-parra-abstract-art.jpg', N'Nike SB Dunk Low Parra Abstract Art', 1100000, 950000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (238, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-pink-pigeon.jpg', N'Nike SB Dunk Low Pink ''Pigeon''', 1080000, 900000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (239, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-pro-dark-russet-cedar.jpg', N'Nike SB Dunk Low ''Pro Dark Russet Cedar''', 1080000, 900000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (240, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-se-easter.jpg', N'Nike SB Dunk Low SE Easter', 1100000, 950000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (241, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-summit-white-wolf-grey.jpg', N'Nike SB Dunk Low ''Summit White Wolf Grey''', 1080000, 900000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (242, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-team-green.jpg', N'Nike SB Dunk Low Team Green', 1100000, 950000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (243, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-wheat.jpg', N'Nike SB Dunk Low ''Wheat''', 1080000, 900000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (244, NULL, NULL, NULL, NULL, N'nike-sb-dunk-low-x-dior-grey-sail.jpg', N'Nike SB Dunk Low x Dior Grey Sail', 1200000, 1000000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (245, NULL, NULL, NULL, NULL, N'nike-sb-dunk-travis-scott-nam-nu.jpg', N'Nike SB Dunk Low ''Travis Scott''', 1080000, 900000, NULL, 13, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (246, NULL, NULL, NULL, NULL, N'stan-smith-bac-nam-nu.jpg', N'Stan Smith bạc nam nữ', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (247, NULL, NULL, NULL, NULL, N'stan-smith-blue-flow-nam-nu.jpg', N'Stan Smith Blue Flow nam nữ', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (248, NULL, NULL, NULL, NULL, N'stan-smith-cau-vong.jpg', N'Stan Smith cầu vàng - Hologram', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (249, NULL, NULL, NULL, NULL, N'stan-smith-den-nam-nu.jpg', N'Stan Smith Đen nam, nữ', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (250, NULL, NULL, NULL, NULL, N'stan-smith-duoi-den.jpg', N'Stan Smith Đen xanh navy', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (251, NULL, NULL, NULL, NULL, N'stan-smith-duoi-hoa-nu.jpg', N'Stan Smith đuôi hoa nữ', 480000, 400000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (252, NULL, NULL, NULL, NULL, N'stan-smith-duoi-hong-nu.jpg', N'Stan Smith đuôi hồng nữ', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (253, NULL, NULL, NULL, NULL, N'stan-smith-pride-nhieu-mau-nam-nu.jpg', N'Stan Smith Pride - nhiều màu nam, nữ', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (254, NULL, NULL, NULL, NULL, N'stan-smith-rio-nam-nu.jpg', N'STAN SMITH RIO NAM, Nữ', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (255, NULL, NULL, NULL, NULL, N'stan-smith-trang-full.jpg', N'Stan smith trắng full nam, nữ', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (256, NULL, NULL, NULL, NULL, N'stan-smith-xanh-la.jpg', N'Stan Smith xanh lá nam, nữ', 780000, 650000, NULL, 7, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (257, NULL, NULL, NULL, NULL, N'xvessel-g-o-p-black-white-1-1.jpg', N'Xvessel G.O.P Black White', 1140000, 1000000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (258, NULL, NULL, NULL, NULL, N'xvessel-g-o-p-slip-on-all-white-1-1.jpg', N'Xvessel G.O.P Slip On All White', 1200000, 1000000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (259, NULL, NULL, NULL, NULL, N'xvessel-g-o-p-slip-on-black-white-1-1.jpg', N'Xvessel G.O.P Slip On Black White', 1200000, 1000000, NULL, 21, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (260, NULL, NULL, NULL, NULL, N'yeezy-1-1.jpg', N'Adidas Yeezy 350 V2 Sesame', 1200000, 1000000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (261, NULL, NULL, NULL, NULL, N'yeezy-350-v2-beluga-2-0-nam-nu-rep-1-1.jpg', N'Yeezy 350 V2 Beluga 2.0 Nam, Nữ', 1200000, 1000000, NULL, 5, NULL)
INSERT [dbo].[product] ([id], [created_by], [created_date], [modified_by], [modified_date], [avatar], [name], [price], [promotion_price], [category_id], [product_line_id], [promotion_id]) VALUES (262, NULL, NULL, NULL, NULL, N'zx-2k-boost-core-black-orange-cyan-1-1.jpg', N'Adidas ZX 2K Boost Core Black Orange Cyan', 1200000, 800000, NULL, 5, NULL)
SET IDENTITY_INSERT [dbo].[product] OFF
GO
SET IDENTITY_INSERT [dbo].[product_line] ON 

INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (1, NULL, NULL, NULL, NULL, N'X9000L4', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (2, NULL, NULL, NULL, NULL, N'EQT+', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (3, NULL, NULL, NULL, NULL, N'ZX 2K Boost', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (4, NULL, NULL, NULL, NULL, N'Ultra Boost', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (5, NULL, NULL, NULL, NULL, N'Yeezy', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (6, NULL, NULL, NULL, NULL, N'Alphabounce', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (7, NULL, NULL, NULL, NULL, N'Stan Smith', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (8, NULL, NULL, NULL, NULL, N'Prophere', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (9, NULL, NULL, NULL, NULL, N'Superstar', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (10, NULL, NULL, NULL, NULL, N'NMD Humanrace', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (11, NULL, NULL, NULL, NULL, N'Ozweego', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (12, NULL, NULL, NULL, NULL, N'Adidas Yung', 1)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (13, NULL, NULL, NULL, NULL, N'SB Dunk', 2)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (14, NULL, NULL, NULL, NULL, N'Jordan', 2)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (15, NULL, NULL, NULL, NULL, N'Air Force 1', 2)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (16, NULL, NULL, NULL, NULL, N'Pegasus', 2)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (17, NULL, NULL, NULL, NULL, N'Air Max', 2)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (18, NULL, NULL, NULL, NULL, N'Joyride', 2)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (19, NULL, NULL, NULL, NULL, N'M2K', 2)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (20, NULL, NULL, NULL, NULL, N'Uptempo', 2)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (21, NULL, NULL, NULL, NULL, N'Converse', 5)
INSERT [dbo].[product_line] ([id], [created_by], [created_date], [modified_by], [modified_date], [name], [brand_id]) VALUES (22, NULL, NULL, NULL, NULL, N'McQueen', 4)
SET IDENTITY_INSERT [dbo].[product_line] OFF
GO
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 3)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 4)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 8)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 8)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 9)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 9)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 10)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 13)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 22)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 26)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 28)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 30)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 33)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 38)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 47)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 53)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 54)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 55)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 58)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 60)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 61)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 74)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 80)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 82)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 83)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 86)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 91)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 95)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 102)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 110)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 111)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 116)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 120)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 127)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 131)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 132)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 136)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 149)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 150)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 154)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 155)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 155)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 168)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 177)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 182)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 186)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 191)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 198)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 201)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 203)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 206)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 208)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 209)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 217)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 218)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 222)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (1, 223)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 226)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 234)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 235)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 237)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 247)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 249)
INSERT [dbo].[product_tag] ([tag_id], [product_id]) VALUES (2, 250)
GO
SET IDENTITY_INSERT [dbo].[tag] ON 

INSERT [dbo].[tag] ([id], [created_by], [created_date], [modified_by], [modified_date], [name]) VALUES (1, NULL, NULL, NULL, NULL, N'best sell')
INSERT [dbo].[tag] ([id], [created_by], [created_date], [modified_by], [modified_date], [name]) VALUES (2, NULL, NULL, NULL, NULL, N'new product')
SET IDENTITY_INSERT [dbo].[tag] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_dwk6cx0afu8bs9o4t536v1j5v]    Script Date: 4/14/2022 1:00:23 PM ******/
ALTER TABLE [dbo].[customer] ADD  CONSTRAINT [UK_dwk6cx0afu8bs9o4t536v1j5v] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_o3uty20c6csmx5y4uk2tc5r4m]    Script Date: 4/14/2022 1:00:23 PM ******/
ALTER TABLE [dbo].[customer] ADD  CONSTRAINT [UK_o3uty20c6csmx5y4uk2tc5r4m] UNIQUE NONCLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_8mx44qvbn71lwrou3igoc1nwm]    Script Date: 4/14/2022 1:00:23 PM ******/
ALTER TABLE [dbo].[size] ADD  CONSTRAINT [UK_8mx44qvbn71lwrou3igoc1nwm] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[customer_address]  WITH CHECK ADD  CONSTRAINT [FKr9ofa0ydsgbaqmt9leb3v5eii] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[customer_address] CHECK CONSTRAINT [FKr9ofa0ydsgbaqmt9leb3v5eii]
GO
ALTER TABLE [dbo].[customer_address]  WITH CHECK ADD  CONSTRAINT [FKsvxvq2qjr406k3l7ws3i0mwy6] FOREIGN KEY([address_id])
REFERENCES [dbo].[address] ([id])
GO
ALTER TABLE [dbo].[customer_address] CHECK CONSTRAINT [FKsvxvq2qjr406k3l7ws3i0mwy6]
GO
ALTER TABLE [dbo].[evaluation]  WITH CHECK ADD  CONSTRAINT [FKds47n6641c5txhs3qwsp3vlxm] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[evaluation] CHECK CONSTRAINT [FKds47n6641c5txhs3qwsp3vlxm]
GO
ALTER TABLE [dbo].[evaluation]  WITH CHECK ADD  CONSTRAINT [FKp9ud5s1ywm0p6ok50jmg0g7ti] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[evaluation] CHECK CONSTRAINT [FKp9ud5s1ywm0p6ok50jmg0g7ti]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FKbwusgmfhhhgdcmrefkvsxhi47] FOREIGN KEY([product_size_id])
REFERENCES [dbo].[product_size] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FKbwusgmfhhhgdcmrefkvsxhi47]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FKrws2q0si6oyd6il8gqe2aennc] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FKrws2q0si6oyd6il8gqe2aennc]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK624gtjin3po807j3vix093tlf] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK624gtjin3po807j3vix093tlf]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK1mtsbur82frn64de7balymq9s] FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK1mtsbur82frn64de7balymq9s]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FKcli9x921yidy04cx25k6m46fy] FOREIGN KEY([promotion_id])
REFERENCES [dbo].[promotion] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FKcli9x921yidy04cx25k6m46fy]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FKcygnye4qt4oifs3gmfrudadj0] FOREIGN KEY([product_line_id])
REFERENCES [dbo].[product_line] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FKcygnye4qt4oifs3gmfrudadj0]
GO
ALTER TABLE [dbo].[product_image]  WITH CHECK ADD  CONSTRAINT [FK6oo0cvcdtb6qmwsga468uuukk] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product_image] CHECK CONSTRAINT [FK6oo0cvcdtb6qmwsga468uuukk]
GO
ALTER TABLE [dbo].[product_line]  WITH CHECK ADD  CONSTRAINT [FK7pc95j6au455lhy5l2xjvk4j9] FOREIGN KEY([brand_id])
REFERENCES [dbo].[brand] ([id])
GO
ALTER TABLE [dbo].[product_line] CHECK CONSTRAINT [FK7pc95j6au455lhy5l2xjvk4j9]
GO
ALTER TABLE [dbo].[product_size]  WITH CHECK ADD  CONSTRAINT [FK8i3jm2ctt0lsyeik2wt76yvv0] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product_size] CHECK CONSTRAINT [FK8i3jm2ctt0lsyeik2wt76yvv0]
GO
ALTER TABLE [dbo].[product_size]  WITH CHECK ADD  CONSTRAINT [FKnlkh5xcjuopsnoimdvmumioia] FOREIGN KEY([size_id])
REFERENCES [dbo].[size] ([id])
GO
ALTER TABLE [dbo].[product_size] CHECK CONSTRAINT [FKnlkh5xcjuopsnoimdvmumioia]
GO
ALTER TABLE [dbo].[product_tag]  WITH CHECK ADD  CONSTRAINT [FK2rf7w3d88x20p7vuc2m9mvv91] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product_tag] CHECK CONSTRAINT [FK2rf7w3d88x20p7vuc2m9mvv91]
GO
ALTER TABLE [dbo].[product_tag]  WITH CHECK ADD  CONSTRAINT [FK3b3a7hu5g2kh24wf0cwv3lgsm] FOREIGN KEY([tag_id])
REFERENCES [dbo].[tag] ([id])
GO
ALTER TABLE [dbo].[product_tag] CHECK CONSTRAINT [FK3b3a7hu5g2kh24wf0cwv3lgsm]
GO
USE [master]
GO
ALTER DATABASE [ShoeShopVer1] SET  READ_WRITE 
GO
