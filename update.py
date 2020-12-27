import argparse
import logging
import shutil
import subprocess
import traceback
from remove_gpl import contains_gpl, remove_gpl

SVN_PREFIX = "/trunk/weka/"
BLACKLISTED_FILES=[
    # maven
    "/trunk/weka/pom.xml",
    # non-api classes
    "/trunk/weka/src/main/java/weka/associations/Apriori.java",
    "/trunk/weka/src/main/java/weka/associations/AprioriItemSet.java",
    "/trunk/weka/src/main/java/weka/associations/CheckAssociator.java",
    "/trunk/weka/src/main/java/weka/associations/FilteredAssociator.java",
    "/trunk/weka/src/main/java/weka/associations/FilteredAssociationRules.java",
    "/trunk/weka/src/main/java/weka/associations/FPGrowth.java",
    "/trunk/weka/src/main/java/weka/associations/SingleAssociatorEnhancer.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/BestFirst.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/CfsSubsetEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/CheckAttributeSelection.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/ClassifierAttributeEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/ClassifierSubsetEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/CorrelationAttributeEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/GainRatioAttributeEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/GreedyStepwise.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/InfoGainAttributeEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/OneRAttributeEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/PrincipalComponents.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/ReliefFAttributeEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/SymmetricalUncertAttributeEval.java",
    "/trunk/weka/src/main/java/weka/attributeSelection/WrapperSubsetEval.java",
    "/trunk/weka/src/main/java/weka/classifiers/BVDecompose.java",
    "/trunk/weka/src/main/java/weka/classifiers/BVDecomposeSegCVSub.java",
    "/trunk/weka/src/main/java/weka/classifiers/CheckClassifier.java",
    "/trunk/weka/src/main/java/weka/classifiers/CheckSource.java",
    "/trunk/weka/src/main/java/weka/classifiers/IteratedSingleClassifierEnhancer.java",
    "/trunk/weka/src/main/java/weka/classifiers/MultipleClassifiersCombiner.java",
    "/trunk/weka/src/main/java/weka/classifiers/ParallelIteratedSingleClassifierEnhancer.java",
    "/trunk/weka/src/main/java/weka/classifiers/ParallelMultipleClassifiersCombiner.java",
    "/trunk/weka/src/main/java/weka/classifiers/RandomizableClassifier.java",
    "/trunk/weka/src/main/java/weka/classifiers/RandomizableIteratedSingleClassifierEnhancer.java",
    "/trunk/weka/src/main/java/weka/classifiers/RandomizableMultipleClassifiersCombiner.java",
    "/trunk/weka/src/main/java/weka/classifiers/RandomizableParallelIteratedSingleClassifierEnhancer.java",
    "/trunk/weka/src/main/java/weka/classifiers/RandomizableParallelMultipleClassifiersCombiner.java",
    "/trunk/weka/src/main/java/weka/classifiers/RandomizableSingleClassifierEnhancer.java",
    "/trunk/weka/src/main/java/weka/classifiers/evaluation/output/prediction/CSV.java",
    "/trunk/weka/src/main/java/weka/classifiers/evaluation/output/prediction/HTML.java",
    "/trunk/weka/src/main/java/weka/classifiers/evaluation/output/prediction/InMemory.java",
    "/trunk/weka/src/main/java/weka/classifiers/misc/SerializedClassifier.java",
    "/trunk/weka/src/main/java/weka/classifiers/rules/DecisionTable.java",
    "/trunk/weka/src/main/java/weka/classifiers/rules/DecisionTableHashKey.java",
    "/trunk/weka/src/main/java/weka/classifiers/rules/JRip.java",
    "/trunk/weka/src/main/java/weka/classifiers/rules/M5Rules.java",
    "/trunk/weka/src/main/java/weka/classifiers/rules/OneR.java",
    "/trunk/weka/src/main/java/weka/classifiers/rules/PART.java",
    "/trunk/weka/src/main/java/weka/classifiers/rules/Rule.java",
    "/trunk/weka/src/main/java/weka/classifiers/rules/RuleStats.java",
    "/trunk/weka/src/main/java/weka/clusterers/Canopy.java",
    "/trunk/weka/src/main/java/weka/clusterers/CheckClusterer.java",
    "/trunk/weka/src/main/java/weka/clusterers/Cobweb.java",
    "/trunk/weka/src/main/java/weka/clusterers/EM.java",
    "/trunk/weka/src/main/java/weka/clusterers/FarthestFirst.java",
    "/trunk/weka/src/main/java/weka/clusterers/FilteredClusterer.java",
    "/trunk/weka/src/main/java/weka/clusterers/HierarchicalClusterer.java",
    "/trunk/weka/src/main/java/weka/clusterers/MakeDensityBasedClusterer.java",
    "/trunk/weka/src/main/java/weka/clusterers/RandomizableClusterer.java",
    "/trunk/weka/src/main/java/weka/clusterers/RandomizableDensityBasedClusterer.java",
    "/trunk/weka/src/main/java/weka/clusterers/RandomizableSingleClustererEnhancer.java",
    "/trunk/weka/src/main/java/weka/clusterers/SimpleKMeans.java",
    "/trunk/weka/src/main/java/weka/clusterers/SingleClustererEnhancer.java",
    "/trunk/weka/src/main/java/weka/core/Check.java",
    "/trunk/weka/src/main/java/weka/core/CheckGOE.java",
    "/trunk/weka/src/main/java/weka/core/CheckOptionHandler.java",
    "/trunk/weka/src/main/java/weka/core/CheckScheme.java",
    "/trunk/weka/src/main/java/weka/core/ChebyshevDistance.java",
    "/trunk/weka/src/main/java/weka/core/ContingencyTables.java",
    "/trunk/weka/src/main/java/weka/core/ManhattanDistance.java",
    "/trunk/weka/src/main/java/weka/core/MinkowskiDistance.java",
    "/trunk/weka/src/main/java/weka/core/Stopwords.java",
    "/trunk/weka/src/main/java/weka/core/TestInstances.java",
    "/trunk/weka/src/main/java/weka/core/FilteredDistance.java",
    "/trunk/weka/src/main/java/weka/core/AllJavadoc.java",
    "/trunk/weka/src/main/java/weka/core/Javadoc.java",
    "/trunk/weka/src/main/java/weka/core/TechnicalInformationHandlerJavadoc.java",
    "/trunk/weka/src/main/java/weka/core/OptionHandlerJavadoc.java",
    "/trunk/weka/src/main/java/weka/core/GlobalInfoJavadoc.java",
    "/trunk/weka/src/main/java/weka/core/converters/C45Loader.java",
    "/trunk/weka/src/main/java/weka/core/converters/C45Saver.java",
    "/trunk/weka/src/main/java/weka/core/converters/CSVLoader.java",
    "/trunk/weka/src/main/java/weka/core/converters/CSVSaver.java",
    "/trunk/weka/src/main/java/weka/core/converters/LibSVMLoader.java",
    "/trunk/weka/src/main/java/weka/core/converters/LibSVMSaver.java",
    "/trunk/weka/src/main/java/weka/core/converters/MatlabLoader.java",
    "/trunk/weka/src/main/java/weka/core/converters/MatlabSaver.java",
    "/trunk/weka/src/main/java/weka/core/converters/SVMLightLoader.java",
    "/trunk/weka/src/main/java/weka/core/converters/SVMLightSaver.java",
    "/trunk/weka/src/main/java/weka/core/converters/SerializedInstancesLoader.java",
    "/trunk/weka/src/main/java/weka/core/converters/SerializedInstancesSaver.java",
    "/trunk/weka/src/main/java/weka/core/converters/TextDirectoryLoader.java",
    "/trunk/weka/src/main/java/weka/core/neighboursearch/BallTree.java",
    "/trunk/weka/src/main/java/weka/core/neighboursearch/CoverTree.java",
    "/trunk/weka/src/main/java/weka/core/neighboursearch/KDTree.java",
    "/trunk/weka/src/main/java/weka/core/neighboursearch/FilteredNeighbourSearch.java",
    "/trunk/weka/src/main/java/weka/core/stemmers/IteratedLovinsStemmer.java",
    "/trunk/weka/src/main/java/weka/core/stemmers/LovinsStemmer.java",
    "/trunk/weka/src/main/java/weka/core/stopwords/AbstractFileBasedStopwords.java",
    "/trunk/weka/src/main/java/weka/core/stopwords/MultiStopwords.java",
    "/trunk/weka/src/main/java/weka/core/stopwords/Rainbow.java",
    "/trunk/weka/src/main/java/weka/core/stopwords/RegExpFromFile.java",
    "/trunk/weka/src/main/java/weka/core/stopwords/WordsFromFile.java",
    "/trunk/weka/src/main/java/weka/core/tokenizers/AlphabeticTokenizer.java",
    "/trunk/weka/src/main/java/weka/core/tokenizers/CharacterNGramTokenizer.java",
    "/trunk/weka/src/main/java/weka/core/tokenizers/NGramTokenizer.java",
    "/trunk/weka/src/main/java/weka/estimators/CheckEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/DDConditionalEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/DiscreteEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/DKConditionalEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/DNConditionalEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/KDConditionalEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/KernelEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/KKConditionalEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/MahalanobisEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/MultivariateGaussianEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/NDConditionalEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/NNConditionalEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/NormalEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/PoissonEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/UnivariateEqualFrequencyHistogramEstimator.java",
    "/trunk/weka/src/main/java/weka/estimators/UnivariateMixtureEstimator.java",
    "/trunk/weka/src/main/java/weka/filters/AllFilter.java",
    "/trunk/weka/src/main/java/weka/filters/CheckSource.java",
    "/trunk/weka/src/main/java/weka/filters/MultiFilter.java",
    "/trunk/weka/src/main/java/weka/filters/RenameRelation.java",
    # package management related
    "/trunk/weka/src/main/java/weka/PluginManager.props",
    "/trunk/weka/src/main/java/weka/core/Utils.java",
    "/trunk/weka/src/main/java/weka/core/ResourceUtils.java",
    "/trunk/weka/src/main/java/weka/core/PluginManager.java",
    "/trunk/weka/src/main/java/weka/core/WekaPackageLibIsolatingClassLoader.java",
    "/trunk/weka/src/main/java/weka/core/WekaPackageManager.java",
    "/trunk/weka/src/main/java/weka/core/WekaPackageClassLoaderManager.java",
    "/trunk/weka/src/main/java/weka/core/converters/ConverterResources.java",
    "/trunk/weka/src/main/java/weka/core/converters/ConverterUtils.java",
    # evaluation
    "/trunk/weka/src/main/java/weka/classifiers/Evaluation.java",
    "/trunk/weka/src/main/java/weka/classifiers/evaluation/Evaluation.java",
    # config files
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.hsql",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.msaccess",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.mssqlserver",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.mssqlserver2005",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.mysql",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.odbc",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.oracle",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.postgresql",
    "/trunk/weka/src/main/java/weka/experiment/DatabaseUtils.props.sqlite3",
    "/trunk/weka/src/main/java/weka/experiment/remote.policy",
    "/trunk/weka/src/main/java/weka/experiment/remote.policy.example",
]

BLACKLISTED_PATHS=[
    "/trunk/weka/src/main/java/weka/classifiers/bayes",
    "/trunk/weka/src/main/java/weka/classifiers/functions",
    "/trunk/weka/src/main/java/weka/classifiers/lazy",
    "/trunk/weka/src/main/java/weka/classifiers/meta",
    "/trunk/weka/src/main/java/weka/classifiers/pmml",
    "/trunk/weka/src/main/java/weka/classifiers/rules/part",
    "/trunk/weka/src/main/java/weka/classifiers/scripting",
    "/trunk/weka/src/main/java/weka/classifiers/trees",
    "/trunk/weka/src/main/java/weka/classifiers/xml",
    "/trunk/weka/src/main/java/weka/core/expressionlanguage",
    "/trunk/weka/src/main/java/weka/core/json",
    "/trunk/weka/src/main/java/weka/core/logging",
    "/trunk/weka/src/main/java/weka/core/metastore",
    "/trunk/weka/src/main/java/weka/core/neighboursearch/balltrees",
    "/trunk/weka/src/main/java/weka/core/neighboursearch/covertrees",
    "/trunk/weka/src/main/java/weka/core/neighboursearch/kdtrees",
    "/trunk/weka/src/main/java/weka/core/packageManagement",
    "/trunk/weka/src/main/java/weka/core/pmml",
    "/trunk/weka/src/main/java/weka/core/scripting",
    "/trunk/weka/src/main/java/weka/core/xml",
    "/trunk/weka/src/main/java/weka/datagenerators/classifiers",
    "/trunk/weka/src/main/java/weka/datagenerators/clusterers",
    "/trunk/weka/src/main/java/weka/filters/supervised",
    "/trunk/weka/src/main/java/weka/filters/unsupervised",
    "/trunk/weka/src/main/java/weka/knowledgeflow",
    "/trunk/weka/src/main/scripts",
    "/trunk/weka/src/test",
]

MODIFIED_FILES = [
    "/trunk/weka/src/main/java/weka/attributeSelection/AttributeSelection.java",
    "/trunk/weka/src/main/java/weka/classifiers/evaluation/AbstractEvaluationMetric.java",
    "/trunk/weka/src/main/java/weka/classifiers/evaluation/Evaluation.java",
    "/trunk/weka/src/main/java/weka/classifiers/evaluation/EvaluationMetricHelper.java",
    "/trunk/weka/src/main/java/weka/classifiers/Evaluation.java",
    "/trunk/weka/src/main/java/weka/clusterers/ClusterEvaluation.java",
    "/trunk/weka/src/main/java/weka/core/converters/ConverterUtils.java",
    "/trunk/weka/src/main/java/weka/core/DictionaryBuilder.java",
    "/trunk/weka/src/main/java/weka/core/EnumHelper.java",
    "/trunk/weka/src/main/java/weka/core/InheritanceUtils.java",
    "/trunk/weka/src/main/java/weka/core/OptionMetaData.java",
    "/trunk/weka/src/main/java/weka/core/ResourceUtils.java",
    "/trunk/weka/src/main/java/weka/core/SerializationHelper.java",
    "/trunk/weka/src/main/java/weka/core/SerializedObject.java",
    "/trunk/weka/src/main/java/weka/core/SystemInfo.java",
    "/trunk/weka/src/main/java/weka/core/Utils.java",
]

POM_VERSION_TAG = "<!-- tiny-weka-version -->"
""" Comment tag in pom.xml to identify the version """

POM_VERSION_TEMPLATE = "  <version>3.9.%i-SNAPSHOT</version><!-- tiny-weka-version -->\n"
""" Template for inserting the new version """

README_REVISION_COMMENT = "# svn revision"
""" The comment to identify the line with the revision """

README_REVISION_TEMPLATE = "r%i   # svn revision\n"
""" The template for the revision in the README """

README_VERSION_TAG = "<!-- tiny-weka-version -->"
""" The tag to identify the line with the revision """

README_VERSION_TEMPLATE = "  <version>3.9.%i-SNAPSHOT</version><!-- tiny-weka-version -->\n"
""" The template for the version in the README """

logger = logging.getLogger("tiny-weka-update")
""" the logging instance to use """


def output_process_info(res):
    """
    Outputs process information.

    :param res: the complete process
    :type res: subprocess.CompletedProcess
    """
    logger.debug("command: %s" % " ".join(res.args))
    if len(res.stdout) > 0:
        logger.debug("stdout:\n%s" % res.stdout.decode("UTF-8"))
    if len(res.stderr) > 0:
        logger.debug("stderr:\n%s" % res.stderr.decode("UTF-8"))


def update_pom(revision):
    """
    Updates the pom.xml with the revision.

    :param revision: the final revision
    :type revision: int
    """

    logger.info("Updating pom.xml")
    with open("./pom.xml", "r") as pom_file:
        lines = pom_file.readlines()
    for i in range(len(lines)):
        if POM_VERSION_TAG in lines[i]:
            lines[i] = POM_VERSION_TEMPLATE % revision
    with open("./pom.xml", "w") as pom_file:
        pom_file.writelines(lines)


def update_readme(revision):
    """
    Updates the README.md with the revision.

    :param revision: the final revision
    :type revision: int
    """

    logger.info("Updating README.md")
    with open("./README.md", "r") as pom_file:
        lines = pom_file.readlines()
    for i in range(len(lines)):
        if README_REVISION_COMMENT in lines[i]:
            lines[i] = README_REVISION_TEMPLATE % revision
        if README_VERSION_TAG in lines[i]:
            lines[i] = README_VERSION_TEMPLATE % revision
    with open("./README.md", "w") as pom_file:
        pom_file.writelines(lines)


def requires_post_processing(source):
    """
    Determines whether the file requires post-processing.
    Looks for: @ProgrammaticProperty, @FilePropertyMetadata

    :param source: the file to check
    :type source: str
    :return: True if the file requires post-processing
    :rtype: bool
    """
    with open(source, "r") as source_file:
        lines = source_file.readlines()
    result = contains_gpl(lines)
    if not result:
        for line in lines:
            if "@ProgrammaticProperty" in line:
                result = True
                break
            if "@FilePropertyMetadata" in line:
                result = True
                break
    return result


def post_process(source, target):
    """
    Post-processes the source content before writing it to its target.

    :param source: the source file
    :type source: str
    :param target: the target file
    :type target: str
    """

    logger.info("Post-processing %s" % source)

    with open(source, "r") as source_file:
        lines = source_file.readlines()

    filtered = []
    is_open = False
    lines = remove_gpl(lines)

    for i in range(len(lines)):
        if is_open:
            if lines[i].strip().endswith(")"):
                is_open = False
                continue
        # remove import of annotations
        if "import " in lines[i]:
            if "weka.gui.FilePropertyMetadata;" in lines[i]:
                continue
            if "weka.gui.ProgrammaticProperty;" in lines[i]:
                continue
        # remove annotations itself
        if "@ProgrammaticProperty" in lines[i]:
            continue
        if "@FilePropertyMetadata" in lines[i]:
            if lines[i].strip().endswith(")"):
                continue
            else:
                is_open = True
                continue

        filtered.append(lines[i])

    with open(target, "w") as target_file:
        target_file.writelines(filtered)



def process_paths(weka, paths, dry_run, verbose):
    """
    The paths in the svn log to process.

    :param weka: the directory with the Weka subversion checkout (HEAD)
    :type weka: str
    :param paths: the list of svn paths to process
    :type paths: list
    :param dry_run: whether to perform a dry-run, i.e., simulation
    :type dry_run: bool
    :param verbose: whether to be verbose with the output
    :type verbose: bool
    :return: the number of files updated
    :rtype: int
    """

    result = 0

    for path in paths:
        # skip all paths that we don't care about
        if not path.startswith(SVN_PREFIX):
            continue

        # check whether path is a manually modified file
        modified = False
        if path in MODIFIED_FILES:
            modified = True
            logger.warning("Modified file was updated: %s" % path)

        # check whether path is blacklisted
        blacklisted = False
        # check files
        if path in BLACKLISTED_FILES:
            if verbose:
                logger.info("Blacklisted: %s" % path)
            blacklisted = True
        # check paths
        if not blacklisted:
            for dir in BLACKLISTED_PATHS:
                if path.startswith(dir):
                    if verbose:
                        logger.info("Blacklisted: %s" % path)
                    blacklisted = True
                    break
        if blacklisted or modified:
            continue

        if dry_run:
            logger.info("Update required: %s" % path)
        else:
            logger.info("Updating: %s" % path)

        # generate filenames
        source = weka + path[len("/trunk"):]  # exclude "/trunk"
        target = "./" + path[len(SVN_PREFIX):]
        # all non-java files into resources
        if not target.endswith(".java"):
            target = target.replace("java", "resources")
        if verbose:
            logger.info("%s\n->%s" % (source, target))
        result += 1

        # copy file
        if not dry_run:
            try:
                if requires_post_processing(source):
                    post_process(source, target)
                else:
                    shutil.copyfile(source, target)
            except:
                logger.error("Failed to copy %s to %s\n%s" % (source, target, traceback.format_exc()))

    return result


def update(weka, revision, svn=None, dry_run=False, verbose=False):
    """
    Updates the code base startin from the specified revision.

    :param weka: the directory with the Weka subversion checkout (HEAD)
    :type weka: str
    :param revision: the revision to start from
    :type revision: int
    :param svn: the svn executable, if not found on PATH; None to use default
    :type svn: str
    :param dry_run: whether to perform a dry-run, i.e., simulation
    :type dry_run: bool
    :param verbose: whether to be verbose with the output
    :type verbose: bool
    """

    if svn is None:
        svn = "svn"
    logger.info("svn executable: %s" % svn)

    # update Weka
    logger.info("Updating Weka code base: %s" % weka)
    res = subprocess.run([svn, "update"], cwd=weka, capture_output=True)
    if res.returncode != 0:
        raise IOError("Failed to update Weka code base!\nStdout:\n%s\nStderr:\n%s" % (res.stdout, res.stderr))
    elif verbose:
        output_process_info(res)

    # current revision
    revision_head = -1
    logger.info("Determining revision of HEAD")
    res = subprocess.run([svn, "info"], cwd=weka, capture_output=True)
    if res.returncode != 0:
        raise IOError("Failed to run svn info!\nStdout:\n%s\nStderr:\n%s" % (res.stdout, res.stderr))
    elif verbose:
        lines = res.stdout.decode("UTF-8").split("\n")
        for line in lines:
            if "Revision:" in line:
                parts = line.split(":")
                if len(parts) == 2:
                    revision_head = int(parts[1].strip())

    # generate log
    logger.info("SVN log since: %i" % revision)
    res = subprocess.run([svn, "log", "-r", "%i:HEAD" % revision, "-v"], cwd=weka, capture_output=True)
    if res.returncode != 0:
        raise IOError("Failed to generate svn log!\nStdout:\n%s\nStderr:\n%s" % (res.stdout, res.stderr))
    elif verbose:
        output_process_info(res)

    # process log
    log = res.stdout.decode("UTF-8")
    entries = log.split("------------------------------------------------------------------------")
    num_files = 0
    for entry in entries:
        if "Changed paths:" in entry:
            parts = entry.split("Changed paths:")
            if len(parts) == 2:
                lines = parts[1].strip().split("\n")
                paths = []
                for line in lines:
                    if (len(line.strip()) > 0) and (SVN_PREFIX + "src" in line):
                        paths.append(line[line.find("/"):])
                # process paths
                if len(paths) > 0:
                    num_files += process_paths(weka, paths, dry_run, verbose)

    logger.info("Number of files that needed updating: %s" % num_files)

    if not dry_run:
        update_pom(revision_head)
        update_readme(revision_head)
        rev_filename = "./update.rev"
        logger.info("Storing revision information in: %s" % rev_filename)
        with open(rev_filename, "w") as ref_file:
            ref_file.write("%i:%i" % (revision, revision_head))


def main(args=None):
    """
    Runs the update. Use -h to see all options.

    :param args: the command-line arguments to use, uses sys.argv if None
    :type args: list
    """

    parser = argparse.ArgumentParser(
        description='Analyzes the svn log from the specified revision on and then updates the code accordingly.\n'
                    + "It stores the start/end svn revision in 'update.rev' after execution.")
    parser.add_argument("-w, --weka", metavar="DIR", dest="weka", required=True, help="the directory with the Weka subversion repository (HEAD)")
    parser.add_argument("-r, --revision", metavar="REV", dest="revision", type=int, required=True, help="the svn revision to start from")
    parser.add_argument("-s, --svn", metavar="EXECUTABLE", dest="svn", help="the svn executable to use if not on the path")
    parser.add_argument("-n, --dry_run", action="store_true", dest="dry_run", help="whether to perform a dry run, i.e., only simulating the update")
    parser.add_argument("-v, --verbose", action="store_true", dest="verbose", help="whether to be verbose with the output")
    parsed = parser.parse_args(args=args)

    logging.basicConfig()
    logger.setLevel(logging.DEBUG)

    update(parsed.weka, parsed.revision, svn=parsed.svn, dry_run=parsed.dry_run, verbose=parsed.verbose)


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


