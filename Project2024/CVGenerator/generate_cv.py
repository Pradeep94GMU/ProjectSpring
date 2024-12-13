import re
from reportlab.lib.pagesizes import LETTER, inch
from reportlab.pdfgen import canvas
from reportlab.lib import colors
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.platypus import Paragraph, Spacer, SimpleDocTemplate, Table, TableStyle, KeepTogether


def parse_jd(file_path):
    """
    Parse the JD file and extract defined sections.
    Adjust the patterns if your JD format changes.
    """
    # Read entire JD content
    with open(file_path, 'r', encoding='utf-8') as f:
        jd_text = f.read()

    # Define a dictionary of sections and their regex patterns
    # The pattern captures everything from the section header until the next section header or the end of text.
    sections = {
        'Company': r'Company\s*(.*?)\n\n(?=[A-Z]|$)',
        'Job Description': r'Job Description\s*(.*?)\n\n(?=[A-Z]|$)',
        'Main Responsibilities': r'Main Responsibilities\s*(.*?)\n\n(?=[A-Z]|$)',
        'Qualifications': r'Qualifications\s*(.*?)\n\n(?=[A-Z]|$)',
        'Expectations': r'Expectations\s*(.*?)\n\n(?=[A-Z]|$)',
        'Leadership': r'Leadership\s*(.*?)\n\n(?=[A-Z]|$)',
        'Collaboration': r'Collaboration\s*(.*?)\n\n(?=[A-Z]|$)',
        'Quality': r'Quality\s*(.*?)\n\n(?=[A-Z]|$)',
        'Wood Mackenzie Values': r'Wood Mackenzie Values\s*(.*?)$'
    }

    data = {}
    for section, pattern in sections.items():
        match = re.search(pattern, jd_text, re.DOTALL)
        if match:
            content = match.group(1).strip()
            # Convert bullet points into a list if they are formatted with dashes or similar
            if '-' in content:
                items = [line.strip().lstrip('-').strip() for line in content.split('\n') if line.strip()]
                data[section] = items
            else:
                # If no dashes found, treat the content as a paragraph
                data[section] = [content]
        else:
            data[section] = []

    return data


def generate_cv(data, output_file="Generated_CV.pdf"):
    """
    Generate a structured PDF CV from the extracted JD data.
    """

    doc = SimpleDocTemplate(output_file, pagesize=LETTER,
                            rightMargin=72, leftMargin=72,
                            topMargin=72, bottomMargin=72)
    styles = getSampleStyleSheet()
    
    # Define custom styles
    title_style = ParagraphStyle(name='Title', fontSize=18, leading=22, spaceAfter=14, alignment=1, textColor=colors.HexColor('#333333'))
    section_header_style = ParagraphStyle(name='SectionHeader', fontSize=14, leading=18, spaceAfter=8, textColor=colors.HexColor('#2E4A7F'))
    body_style = styles['BodyText']
    bullet_style = ParagraphStyle(name='Bullet', parent=body_style, bulletIndent=0, leftIndent=15)

    elements = []

    # Title Section (For demonstration, we'll consider "Role" extracted from Job Description if present)
    # If the JD text doesn't clearly specify a "Role" in the Job Description section, you can hardcode or skip this.
    # Here, let's say the role might be mentioned in the Job Description's first line as a fallback:
    role_line = data.get('Job Description', [""])[0]
    # You could try to guess the role from the first line, or just say "Software Engineer"
    # For simplicity, just use a fixed title here:
    elements.append(Paragraph("<b>Software Engineer</b>", title_style))
    elements.append(Spacer(1, 0.2*inch))

    # Contact Info Section (Hardcoded for demo)
    contact_info = [
        "Name: Your Name",
        "Email: your.email@example.com",
        "Phone: +1-555-555-5555",
        "LinkedIn: linkedin.com/in/yourprofile"
    ]
    contact_table_data = [[Paragraph(item, body_style)] for item in contact_info]
    contact_table = Table(contact_table_data, colWidths=[4*inch])
    contact_table.setStyle(TableStyle([
        ('VALIGN', (0,0), (-1,-1), 'TOP'),
        ('TEXTCOLOR', (0,0), (-1,-1), colors.HexColor('#444444')),
        ('FONTNAME', (0,0), (-1,-1), 'Helvetica')
    ]))
    elements.append(contact_table)
    elements.append(Spacer(1, 0.2*inch))

    # A helper function to add a section
    def add_section(title, content_list):
        if content_list and any(content_list):
            elements.append(Paragraph(title, section_header_style))
            for item in content_list:
                elements.append(Paragraph(f"• {item}", bullet_style))
            elements.append(Spacer(1, 0.2*inch))

    # Add sections from the parsed data
    add_section("Company", data.get('Company', []))
    add_section("Job Description", data.get('Job Description', []))
    add_section("Main Responsibilities", data.get('Main Responsibilities', []))
    add_section("Qualifications", data.get('Qualifications', []))
    add_section("Expectations", data.get('Expectations', []))
    add_section("Leadership", data.get('Leadership', []))
    add_section("Collaboration", data.get('Collaboration', []))
    add_section("Quality", data.get('Quality', []))
    add_section("Wood Mackenzie Values", data.get('Wood Mackenzie Values', []))

    doc.build(elements)


if __name__ == "__main__":
    jd_file = "job_description.txt"  # Update this path to your JD file
    data = parse_jd(jd_file)
    generate_cv(data, output_file="Generated_CV.pdf")
    print("CV generated successfully as Generated_CV.pdf")
