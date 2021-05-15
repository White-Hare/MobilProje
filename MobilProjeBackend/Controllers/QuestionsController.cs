using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MobilProjeBackend.Data;
using MobilProjeBackend.Helpers;
using MobilProjeBackend.Models;

namespace MobilProjeBackend.Controllers
{
    [Route("api/[controller]")]
    [ApiController, Authorize]
    public class QuestionsController : ControllerBase
    {
        private readonly BasicItemsContext _context;

        private Random random;

        public QuestionsController(BasicItemsContext context)
        {
            _context = context;
            random = new Random();
        }

        // GET: api/Quests
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Question>>> GetQuest()
        {
            return await _context.Questions.ToListAsync();
        }

        // GET: api/Quests/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Question>> GetQuest(long id)
        {
            var quest = await _context.Questions.FindAsync(id);

            if (quest == null)
            {
                return NotFound();
            }

            return quest;
        }


        // PUT: api/Quests/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutQuest(long id, Question question)
        {
            if (id != question.Id)
            {
                return BadRequest();
            }

            _context.Entry(question).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!QuestExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Quests
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Question>> PostQuest(Question question)
        {
            _context.Questions.Add(question);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetQuest", new { id = question.Id }, question);
        }

        // DELETE: api/Quests/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteQuest(long id)
        {
            var quest = await _context.Questions.FindAsync(id);
            if (quest == null)
            {
                return NotFound();
            }

            _context.Questions.Remove(quest);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool QuestExists(long id)
        {
            return _context.Questions.Any(e => e.Id == id);
        }

        
        [HttpGet("Random")]
        public async Task<ActionResult<Question>> GetRandomQuest()
        {
            var quests = await _context.Questions.ToListAsync();

            if (quests == null || quests.Count == 0)
            {
                return NotFound();
            }

            return quests[random.Next(0, quests.Count)];
        }
    }
}
