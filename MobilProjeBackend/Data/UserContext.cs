using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MobilProjeBackend.Models;

namespace MobilProjeBackend.Data
{
    public class UserContext : DbContext
    {
        public UserContext(DbContextOptions<UserContext> options) : base(options){}

        public DbSet<MyUser> MyUsers { get; set; }
    }
}
