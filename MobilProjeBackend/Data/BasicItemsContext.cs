using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MobilProjeBackend.Models;

namespace MobilProjeBackend.Data
{
    public class BasicItemsContext : DbContext
    {
        public BasicItemsContext (DbContextOptions<BasicItemsContext> options) : base(options) { }

        public DbSet<Question> Questions { get; set; }
        public DbSet<Profile> Profiles { get; set; }
    }
}
