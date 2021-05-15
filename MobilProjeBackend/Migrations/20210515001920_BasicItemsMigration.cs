using Microsoft.EntityFrameworkCore.Migrations;

namespace MobilProjeBackend.Migrations
{
    public partial class BasicItemsMigration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Profiles",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    TotalCorrectAnswers = table.Column<int>(type: "int", nullable: false),
                    TotalWrongAnswers = table.Column<int>(type: "int", nullable: false),
                    BestScore = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Profiles", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Questions",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    QuestionParagraph = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    OptionsString = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    AnswerIndex = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Questions", x => x.Id);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Profiles");

            migrationBuilder.DropTable(
                name: "Questions");
        }
    }
}
