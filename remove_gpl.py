import argparse
import logging
import os
import re
import traceback


JAVA = [
    (" \\*.*This program is.*", " *"),
    (" \\*.*terms of the GNU.*", " *"),
    (" \\*.*Free Software Foundation.*", " *"),
    (" \\*.*any later version.*", " *"),
    (" \\*.*program is distributed in .*", " *"),
    (" \\*.*WITHOUT ANY WARRANTY.*", " *"),
    (" \\*.*MERCHANTABILITY or FITNESS.*", " *"),
    (" \\*.*Public License for more details.*", " *"),
    (" \\*.*received a copy .*", " *"),
    (" \\*.*along with this program.*", " *"),
]

logger = logging.getLogger("tiny-weka-update")
""" the logging instance to use """


def contains_gpl(lines):
    """
    Checks whether the lines contain a GPL preamble and require updating.

    :param lines: the list of strings to check
    :type lines: list
    :return: True if a GPL preamble was detected
    :rtype: bool
    """
    result = False
    for line in lines:
        for find, _ in JAVA:
            if re.match(find, line):
                result = True
                break
    return result


def remove_gpl(lines):
    """
    Removes the GPL preamble from the lines.

    :param lines: the source code to process
    :type lines: list
    :return: the updated source code
    :rtype: list
    """

    for i in range(len(lines)):
        # remove GPL
        for find, replace in JAVA:
            if re.match(find, lines[i]):
                lines[i] = re.sub(find, replace, lines[i])
                break
    return lines


def remove(dir, recursive, dry_run=False, verbose=False):
    """
    Processes source code files in the specified directory.

    :param dir: the directory to process
    :type dir: str
    :param recursive: whether to look for files recursively
    :type recursive: bool
    :param dry_run: whether to not actually perform the removal
    :type dry_run: bool
    :param verbose: whether to be more verbose with the output
    :type verbose: bool
    """

    if verbose:
        logger.info("Directory: %s" % dir)
    for f in os.listdir(dir):
        if (f == ".") or (f == ".."):
            continue
        path = os.path.join(dir, f)
        if os.path.isdir(path) and recursive:
            remove(path, recursive, dry_run=dry_run, verbose=verbose)
        if f.endswith(".java"):
            if verbose:
                logger.info("Checking: %s" % path)
            with open(path, "r") as sf:
                lines = sf.readlines()
            found = contains_gpl(lines)
            if verbose:
                logger.info("Found GPL preamble? %s" % str(found))
            if found:
                if dry_run:
                    logger.info("Requires update: %s" % path)
                else:
                    logger.info("Updating: %s" + path)
                    lines = remove_gpl(lines)
                    with open(path, "w") as sf:
                        sf.writelines(lines)


def main(args=None):
    """
    Runs the update. Use -h to see all options.

    :param args: the command-line arguments to use, uses sys.argv if None
    :type args: list
    """

    parser = argparse.ArgumentParser(
        description='Removes the GPL preamble from Java source code files.')
    parser.add_argument("-d, --dir", metavar="DIR", dest="dir", required=True, help="the directory with source code files to process")
    parser.add_argument("-r, --recursive", action="store_true", dest="recursive", help="whether to search for source code files recursively")
    parser.add_argument("-n, --dry_run", action="store_true", dest="dry_run", help="whether to perform a dry run, i.e., only simulating the removal")
    parser.add_argument("-v, --verbose", action="store_true", dest="verbose", help="whether to be verbose with the output")
    parsed = parser.parse_args(args=args)

    logging.basicConfig()
    logger.setLevel(logging.DEBUG)

    remove(parsed.dir, parsed.recursive, dry_run=parsed.dry_run, verbose=parsed.verbose)


def sys_main():
    """
    Runs the main function using the system cli arguments, and
    returns a system error code.

    :return: 0 for success, 1 for failure.
    :rtype: int
    """

    try:
        main()
        return 0
    except Exception:
        print(traceback.format_exc())
        return 1


if __name__ == "__main__":
    try:
        main()
    except Exception:
        print(traceback.format_exc())
